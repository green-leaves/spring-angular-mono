package com.greenleaves.spring.gulp.config.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;

/**
 * Created by sgdn001 on 9/23/2016.
 */
public class AuthenticationWithToken extends PreAuthenticatedAuthenticationToken {

    private static final long serialVersionUID = -5603030876641663394L;

    private String token;

    public AuthenticationWithToken(String token) {
        super(null, null);
        this.token = token;
    }

    public AuthenticationWithToken(Object aPrincipal, Object aCredentials, String token) {
        super(aPrincipal, aCredentials);
        this.token = token;
    }

    public AuthenticationWithToken(Object aPrincipal, Object aCredentials, Collection<? extends GrantedAuthority> anAuthorities, String token) {
        super(aPrincipal, aCredentials, anAuthorities);
        this.token = token;
    }

    public AuthenticationWithToken(Authentication auth, String token) {
        super(auth.getPrincipal(), auth.getCredentials(), auth.getAuthorities());
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
