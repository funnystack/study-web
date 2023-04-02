package com.funny.study.ss.controller;

import com.funny.study.ss.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author funnystack
 * @date 2018/3/20 11:22
 */
@RestController
public class LoginController {
    @Autowired
    IUserService userService;

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

}
