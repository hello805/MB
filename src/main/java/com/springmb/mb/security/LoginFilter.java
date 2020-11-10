package com.springmb.mb.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//自定义用户名密码校验过滤类
@Slf4j
@Component
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private CusAuthenticationManager authenticationManager;
    //
    private final  String usernameParameter = "username";
    private final  String passwordParameter = "password";

    /**
     *
     * @param authenticationManager 认证管理器
     * @param adminAuthenticationSuccessHandler 认证成功处理器
     * @param adminAuthenticationFailureHandler 认证失败处理器
     */

    public LoginFilter(CusAuthenticationManager authenticationManager, MyAuthenticationSuccessHandler adminAuthenticationSuccessHandler, MyAuthenticationFailHandler adminAuthenticationFailureHandler) {
        //指定登录认证路径和方法
        super(new AntPathRequestMatcher("/findAll/login", "POST"));
        this.setAuthenticationManager(authenticationManager);
        this.setAuthenticationSuccessHandler(adminAuthenticationSuccessHandler);
        this.setAuthenticationFailureHandler(adminAuthenticationFailureHandler);
    }


    //拦截用户的登录请求数据 并初步校验封装到自己定义的认证对象中 调用自定义认证类进行认证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
         log.info("①用户名密码校验过滤类：拦截登录请求");
        if (request.getContentType() == null) {
            throw new AuthenticationServiceException("请求头类型不支持: " + request.getContentType());
        }
        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);
        LoginAuthenticationToken authenticationToken = new LoginAuthenticationToken(username, password);
        // 设置一些详情信息
        this.setDetails(request, authenticationToken);
        //通过AuthenticationManager调用相应的AuthenticationProvider进行用户认证
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.usernameParameter);
    }

    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(this.passwordParameter);
    }
    protected void setDetails(HttpServletRequest request, LoginAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }


}