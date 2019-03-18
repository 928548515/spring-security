package com.imooc.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCntroller {

    @GetMapping("/hello")
    public String hello(){
        return  "hello security";
    }

}
