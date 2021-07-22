package com.mengyu;

import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.util.ErrorHandler;
import org.springframework.util.SocketUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Filter;

/**
 * @author yuzhang
 * @date 2021/4/22 上午10:26
 * SmartLifecycle：当Spring容器加载和初始化所有bean之后，会接着回调实现该接口的类中对应的start方法
 * 用于在Spring加载和初始化所有后执行一些任务或启动需要的异步服务(此处是用来启动zookeeper服务)
 */
public class EmbeddedZookeeper implements SmartLifecycle {
    private static final Logger logger = LoggerFactory.getLogger(EmbeddedZookeeper.class);
    /**
     * zookeeper运行端口
     */
    private final int clientPort;

    private boolean autoStartup = true;

    private int phase = 0;
    /**
     * 用于运行zookeeper的线程
     */
    private volatile Thread zkServerThread;
    /**
     * zookeeper server
     */
    private volatile ZooKeeperServerMain zkServer;
    /**
     * 当zookeeper server发生异常时会被调用
     */
    private ErrorHandler errorHandler;

    private boolean daemon = true;

    public EmbeddedZookeeper() {
        this.clientPort = SocketUtils.findAvailableTcpPort();
    }

    public EmbeddedZookeeper(int clientPort, boolean daemon) {
        this.clientPort = clientPort;
        this.daemon = daemon;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setAutoStartup(boolean autoStartup) {
        this.autoStartup = autoStartup;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    /**
     * 根据该方法的返回值决定是否执行start方法。<br/>
     * 返回true时start方法会被自动执行，返回false则不会。
     */
    @Override
    public boolean isAutoStartup() {
        return this.autoStartup;
    }

    @Override
    public void stop(Runnable runnable) {
        stop();
        runnable.run();
    }

    /**
     * 1. 我们主要在该方法中启动任务或者其他异步服务，比如开启MQ接收消息<br/>
     * 2. 当上下文被刷新（所有对象已被实例化和初始化之后）时，将调用该方法，默认生命周期处理器将检查每个SmartLifecycle对象的isAutoStartup()方法返回的布尔值。
     * 如果为“true”，则该方法会被调用，而不是等待显式调用自己的start()方法。
     */
    @Override
    public void start() {
        if (zkServerThread == null) {
            zkServerThread = new Thread(new ServerRunnable(),"Zookeeper Server Starter");
            zkServerThread.setDaemon(this.daemon);
            zkServerThread.start();
        }
    }

    /**
     * SmartLifecycle子类的才有的方法，当isRunning方法返回true时，该方法才会被调用。
     */
    @Override
    public void stop() {
        if (zkServerThread != null) {
            try {
                Method shutdown = ZooKeeperServerMain.class.getDeclaredMethod("shutdown");
                shutdown.setAccessible(true);
                shutdown.invoke(zkServer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try {
                zkServerThread.join(5000);
                zkServerThread = null;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("Interrupted while waiting for embeded zookeeper to exit");
                zkServerThread = null;
            }
        }
    }

    /**
     * 1. 只有该方法返回false时，start方法才会被执行。<br/>
     * 2. 只有该方法返回true时，stop(Runnable callback)或stop()方法才会被执行。
     */
    @Override
    public boolean isRunning() {
        return zkServerThread != null;
    }

    /**
     * 如果工程中有多个实现接口SmartLifecycle的类，则这些类的start的执行顺序按getPhase方法返回值从小到大执行。<br/>
     * 例如：1比2先执行，-1比0先执行。 stop方法的执行顺序则相反，getPhase返回值较大类的stop方法先被调用，小的后被调用。
     */
    @Override
    public int getPhase() {
        return this.phase;
    }

    private class ServerRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Properties prop = new Properties();
                File file = new File(System.getProperty("java.io.tmpdir") + File.separator + UUID.randomUUID());
                file.deleteOnExit();
                prop.setProperty("dataDir", file.getAbsolutePath());
                prop.setProperty("clientPort", String.valueOf(clientPort));

                QuorumPeerConfig config = new QuorumPeerConfig();
                config.parseProperties(prop);

                zkServer = new ZooKeeperServerMain();
                ServerConfig config1 = new ServerConfig();
                config1.readFrom(config);

                zkServer.runFromConfig(config1);

            } catch (Exception e) {
                if (errorHandler != null) {
                    errorHandler.handleError(e);
                } else {
                    logger.error("Exception running embedded zookeeper", e  );
                }
            }
        }
    }
}
