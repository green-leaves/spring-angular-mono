package com.greenleaves.spring.gulp.service.impl;

import com.greenleaves.spring.gulp.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sgdn001 on 9/28/2016.
 */
@Service
public class TokenServiceImpl implements TokenService {

    private Map<String, Authentication> tokenMap = new HashMap<>();

    public void put(String token, Authentication authentication) {
        tokenMap.put(token, authentication);
    }

    @Override
    public Authentication retrieveAuth(String token) {
        return tokenMap.get(token);
    }

}
