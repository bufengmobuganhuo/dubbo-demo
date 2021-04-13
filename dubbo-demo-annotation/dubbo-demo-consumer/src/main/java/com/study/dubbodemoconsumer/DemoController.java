package com.study.dubbodemoconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuzhang
 * @date 2021/4/13 下午3:18
 * TODO
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @DubboReference
    DemoService demoService;

    @GetMapping("/{name}")
    public String sayHello(@PathVariable("name")String name) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(demoService.sayHello(name));
    }
}
