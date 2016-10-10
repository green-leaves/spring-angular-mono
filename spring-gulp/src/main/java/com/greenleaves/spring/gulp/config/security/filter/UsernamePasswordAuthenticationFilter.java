package com.greenleaves.spring.gulp.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenleaves.spring.gulp.model.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by sgdn001 on 9/27/2016.
 */
public class UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final Logger logger = LoggerFactory.getLogger(UsernamePasswordAuthenticationFilter.class);

    public UsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super("/login");
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        logger.info("attempt to login");
        ObjectMapper json = new ObjectMapper();

        LoginRequest loginRequest = json.readValue(request.getInputStream(), LoginRequest.class);

        logger.info(request.getInputStream().toString());

        UsernamePasswordAuthenticationToken usernamePasswordAuth = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        return getAuthenticationManager().authenticate(usernamePasswordAuth);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        logger.debug("Authentication success. Updating SecurityContextHolder to contain: " + authResult);
        SecurityContextHolder.getContext().setAuthentication(authResult);

        logger.debug("UsernamePasswordAuthenticationFilter is passing request down the filter chain");
        chain.doFilter(request, response);
    }
}
