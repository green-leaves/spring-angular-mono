package com.greenleaves.spring.gulp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sgdn001 on 7/15/2016.
 */
@RestController
@RequestMapping(value = "/api")
public class AppController {

    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request) {
        //System.out.println(request.getHeader("token-auth"));
        return "hello world 1";
    }
}
