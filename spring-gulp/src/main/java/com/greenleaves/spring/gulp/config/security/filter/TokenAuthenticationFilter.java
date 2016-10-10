package com.greenleaves.spring.gulp.config.security.filter;

import com.greenleaves.spring.gulp.config.security.auth.AuthenticationWithToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sgdn001 on 7/18/2016.
 */
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super("/**");
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return request.getHeader("auth-token") != null;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        LOGGER.info("attempt to authenticate token");
        String token = request.getHeader("auth-token");

        AuthenticationWithToken authRequest = new AuthenticationWithToken(token);
        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        LOGGER.debug("Authentication success. Updating SecurityContextHolder to contain: " + authResult);
        SecurityContextHolder.getContext().setAuthentication(authResult);

        LOGGER.debug("TokenAuthenticationFilter is passing request down the filter chain");
        chain.doFilter(request, response);
    }
}
