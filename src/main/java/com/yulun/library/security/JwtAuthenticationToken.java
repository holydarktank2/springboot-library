package com.yulun.library.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final UserDetails principal;
    private final String token;

    public JwtAuthenticationToken(UserDetails principal, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.token = token;
        setAuthenticated(true); // 表示通過身份驗證
    }

    @Override
    public Object getCredentials() {
        return token; // JWT Token 作為憑據
    }

    @Override
    public Object getPrincipal() {
        return principal; // 使用者資訊
    }
}
