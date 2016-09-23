package com.greenleaves.spring.gulp.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by sgdn001 on 9/23/2016.
 */
public class TokenAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        AuthenticationWithToken authToken = (AuthenticationWithToken) authentication;
        String token = authToken.getToken();

        System.out.println(token);
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
