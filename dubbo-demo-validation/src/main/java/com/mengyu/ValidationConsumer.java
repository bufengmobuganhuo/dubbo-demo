package com.mengyu;

import com.mengyu.api.ValidationParameter;
import com.mengyu.api.ValidationService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Date;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/4/22 上午10:29
 * TODO
 */
public class ValidationConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/validation-consumer.xml");
        ctx.start();

        ValidationService service = (ValidationService) ctx.getBean("validationService");

        ValidationParameter parameter = new ValidationParameter();
        parameter.setName("liangfei");
        parameter.setEmail("liangfei@liang.fei");
        parameter.setAge(50);
        parameter.setLoginDate(new Date(System.currentTimeMillis() - 1000000));
        parameter.setExpiryDate(new Date(System.currentTimeMillis() + 1000000));
        service.save(parameter);
        System.out.println("Validation save ok");

        try {
            parameter.setName(null);
            service.save(parameter);
            System.err.println("Validation save error");
        }catch (Exception e){
            ValidationException ve = (ValidationException) e;
            //Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
            System.out.println(ve);
        }

        service.update(parameter);
        System.out.println("Validation update ok");

        service.delete(2,"abc");
        System.out.println("validation delete ok");

        try{
            service.delete(0,"abc");
            System.err.println("Validation delete error");
        }catch (Exception e){
            ValidationException ve = (ValidationException) e;
            //Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
            System.out.println(ve);
        }

    }
}
