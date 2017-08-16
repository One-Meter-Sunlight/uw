package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 321 on 2017/8/11.
 */
@RestController
@RequestMapping("/web")
public class TestController {

    @Autowired
    IUserService iUserService;


    @RequestMapping("/helloTest")
    public String test() {
        return JSON.toJSONString(iUserService.selectListBySQL());
    }

    @RequestMapping("/helloTest2")
    @Cacheable(value = "user-key")
    public List<User> test2() {
        Page<User> page = new Page<>(1, 15);
        return iUserService.selectList(page);
    }

    @RequestMapping("/uid")
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }


    @Autowired
    public MailService mailService;

    @RequestMapping("/send")
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("1515803528@qq.com", "test simple mail", " hello this is simple mail");
    }
}