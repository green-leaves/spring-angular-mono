package com.greenleaves.spring.gulp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgdn001 on 9/28/2016.
 */
public class UserAuth {
    private String username;
    private List<String> roles = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
