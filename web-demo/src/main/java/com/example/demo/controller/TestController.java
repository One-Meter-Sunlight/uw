package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.entity.User;
import com.example.demo.service.user.IUserService;
import com.example.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * Created by 321 on 2017/8/11.
 */
@RestController
public class TestController {

    @Autowired
    IUserService iUserService;

//    @Autowired
//    TestUserService testUserService;

//    @RequestMapping("/testFeign")
//    public void testFeign() {
//        testUserService.testFeign();
//    }

    @RequestMapping("/helloTest")
    public String test() {
        return JSON.toJSONString(iUserService.selectListBySQL());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/helloworld")
    public String helloTest() {
        return "helloworld";
    }

    @PreAuthorize("hasAuthority('ADMIN', 'USER')")
    @RequestMapping("/helloworld2")
    public String helloTest2() {
        return "helloworld2";
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