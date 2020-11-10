package com.springmb.mb.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class CusAuthenticationManager implements AuthenticationManager {


    //注入自定义认证类
    private final MyUserProvider myUserProvider;

    public CusAuthenticationManager(MyUserProvider myUserProvider) {
        this.myUserProvider = myUserProvider;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //进行认证
        log.info("②认证管理器调用认证类 来处理认证数据");
        Authentication authenticate = myUserProvider.authenticate(authentication);
        if (Objects.nonNull(authenticate)) {
            return authenticate;
        }
        throw new ProviderNotFoundException("Authentication failed!");
    }







}