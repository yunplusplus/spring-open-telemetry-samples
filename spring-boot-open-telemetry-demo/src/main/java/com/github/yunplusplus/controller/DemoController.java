package com.github.yunplusplus.controller;

import com.github.yunplusplus.model.Address;
import com.github.yunplusplus.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@RestController
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getAddress")
    public Address getAddress(@RequestParam("addr") String addr) {
        Address address = new Address();
        address.setAddr(addr);
        address.setPhoneNumber("130xxxx1111");
        return address;
    }

    @RequestMapping("/getUserInfo")
    public User getUserInfo(@RequestParam("name") String name) {
        ResponseEntity<Address> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/getAddress?addr=iceland", Address.class);
        User user = new User();
        user.setName(name);
        user.setAge(24);
        user.setAddress(responseEntity.getBody());
        return user;
    }
}
