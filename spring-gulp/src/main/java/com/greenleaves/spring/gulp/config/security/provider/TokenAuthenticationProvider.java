package com.greenleaves.spring.gulp.config.security.provider;

import com.greenleaves.spring.gulp.config.security.auth.AuthenticationWithToken;
import com.greenleaves.spring.gulp.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by sgdn001 on 9/23/2016.
 */
public class TokenAuthenticationProvider implements AuthenticationProvider {

    TokenService tokenService;

    public TokenAuthenticationProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        AuthenticationWithToken authToken = (AuthenticationWithToken) authentication;
        String token = authToken.getToken();

        Authentication auth = tokenService.retrieveAuth(token);

        if (auth == null) throw new BadCredentialsException("Invalid Token !");

        return new AuthenticationWithToken(auth, token);
}

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AuthenticationWithToken.class);
    }
}
