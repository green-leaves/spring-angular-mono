package com.greenleaves.spring.gulp.service.impl;

import com.greenleaves.spring.gulp.model.UserAuth;
import com.greenleaves.spring.gulp.service.UserAuthService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by sgdn001 on 9/28/2016.
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Override
    public UserAuth getUserAuth() {
        UserAuth userAuth = new UserAuth();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails != null) {
            userAuth.setUsername(userDetails.getUsername());
            for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
                userAuth.getRoles().add(grantedAuthority.getAuthority());
            }
        }

        return userAuth;
    }
}
