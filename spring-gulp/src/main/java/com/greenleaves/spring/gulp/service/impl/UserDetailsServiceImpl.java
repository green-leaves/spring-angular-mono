package com.greenleaves.spring.gulp.service.impl;

import netscape.security.Privilege;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sgdn001 on 9/23/2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<String> roles = new ArrayList<>();
        if ("admin".equalsIgnoreCase(username)) {
            roles.add("ROLE_ADMIN");
        }
        return new User(username, "123456", true, true, true, true, getAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        return getGrantedAuthorities(roles);
    }

//    private List<String> getPrivileges(Collection<Role> roles) {
//        List<String> privileges = new ArrayList<String>();
//        List<Privilege> collection = new ArrayList<Privilege>();
//        for (Role role : roles) {
//            collection.addAll(role.getPrivileges());
//        }
//        for (Privilege item : collection) {
//            privileges.add(item.getName());
//        }
//        return privileges;
//    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }


}
