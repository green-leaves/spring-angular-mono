package com.greenleaves.spring.gulp.controller;

import com.greenleaves.spring.gulp.model.UserAuth;
import com.greenleaves.spring.gulp.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sgdn001 on 7/15/2016.
 */
@RestController
@RequestMapping(value = "/api")
public class AppController {

    @Autowired
    UserAuthService userAuthService;

    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request) throws Exception {
        //System.out.println(request.getHeader("token-auth"));
        return "success !";
    }

    @RequestMapping(value = "/admin/test")
    public String adminTest(HttpServletRequest request) {
        //System.out.println(request.getHeader("token-auth"));
        return "Admin";
    }

    @RequestMapping(value = "/moderator")
    @PreAuthorize("hasPermission(#user, 'ROLE_MODERATOR')")
    public String moderator() {
        return "Moderator";
    }

    @RequestMapping(value = "/user/auth")
    public UserAuth getUserSecurity() {
        return userAuthService.getUserAuth();
    }
}
