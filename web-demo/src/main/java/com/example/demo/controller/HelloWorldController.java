package com.example.demo.controller;

import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 321 on 2017/8/11.
 */
@RestController
public class HelloWorldController {

    @Autowired
    IUserService iUserService;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World is hot start";
    }

    @RequestMapping("/helloTest")
    public String test() {
        return iUserService.selectListBySQL().toString();
    }
}