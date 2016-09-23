package com.greenleaves.spring.gulp.config;

import com.greenleaves.spring.gulp.config.filter.LogFilter;
import com.greenleaves.spring.gulp.config.filter.TokenAuthenticationProcessingFilter;
import com.greenleaves.spring.gulp.config.security.TokenAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sgdn001 on 7/18/2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().and().authorizeRequests()
            .antMatchers("/**").permitAll()
            .antMatchers("/api/admin/**").hasRole("ADMIN")
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());

        http.addFilterBefore(new TokenAuthenticationProcessingFilter(authenticationManager()), BasicAuthenticationFilter.class);
    }

    @Bean
    LogFilter logFilter() {
        return new LogFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.authenticationProvider(tokenAuthProvider());
    }

    @Bean
    public AuthenticationProvider tokenAuthProvider() {
        TokenAuthenticationProvider tokenAuthProvider = new TokenAuthenticationProvider();
        //authProvider.setUserDetailsService(userDetailsService);
        //authProvider.setPasswordEncoder(passwordEncoder());
        return tokenAuthProvider;
    }

//    @Bean
//    public TokenAuthenticationProcessingFilter tokenAuthenticationProcessingFilter() throws Exception {
//        return new TokenAuthenticationProcessingFilter(authenticationManager());
//    }

    //Disable auto register AuthenticationTokenProcessingFilter
//    @Bean
//    public FilterRegistrationBean registration(AuthenticationTokenProcessingFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }


    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request,
                                 HttpServletResponse response,
                                 AuthenticationException e) throws IOException, ServletException {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        };
    }
}
