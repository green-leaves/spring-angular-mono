package com.greenleaves.spring.gulp.service;

import org.springframework.security.core.Authentication;

/**
 * Created by sgdn001 on 9/28/2016.
 */
public interface TokenService {
    void put(String token, Authentication authentication);
    Authentication retrieveAuth(String token);
}
