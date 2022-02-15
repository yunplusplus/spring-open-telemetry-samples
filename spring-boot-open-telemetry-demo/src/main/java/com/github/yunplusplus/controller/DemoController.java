package com.github.yunplusplus.controller;

import com.github.yunplusplus.model.Address;
import com.github.yunplusplus.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import io.opentelemetry.api.trace.Span;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    private RestTemplate restTemplate;

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @RequestMapping("/getAddress")
    public Address getAddress(@RequestParam("addr") String addr) {
        Address address = new Address();
        address.setAddr(addr);
        address.setPhoneNumber("130xxxx1111");
        executorService.submit(() -> {
            Span newThreadSpan = Span.current();
            logger.info("getAddress  newThreadPool traceId:{}", newThreadSpan.getSpanContext().getTraceId());
        });
        return address;
    }

    @RequestMapping("/getUserInfo")
    public User getUserInfo(@RequestParam("name") String name) {
        ResponseEntity<Address> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/getAddress?addr=iceland", Address.class);
        User user = new User();
        user.setName(name);
        user.setAge(24);
        user.setAddress(responseEntity.getBody());
        Span span = Span.current();
        logger.info("getUserInfo traceId:{}", span.getSpanContext().getTraceId());

        executorService.submit(() -> {
            Span newThreadSpan = Span.current();
            logger.info("getUserInfo  newThreadPool traceId:{}", newThreadSpan.getSpanContext().getTraceId());
        });
        return user;
    }
}
