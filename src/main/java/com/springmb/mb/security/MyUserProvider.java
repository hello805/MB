package com.springmb.mb.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyUserProvider implements AuthenticationProvider {

    @Autowired
    private UserVerify userVerify;
    @Autowired
    private PasswordEncoder passwordEncoder;
        @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //自定义认证方法 返回认证对象
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //authentication 用户的认证对象
        //Token 实现类
        log.info("③认证类获取 用户认证信息 并根据用户名获取数据库存储的用户信息 校验 成功后重新包装");
        //将认证对象转化成自定义认证对象
        LoginAuthenticationToken authenticationToken= (LoginAuthenticationToken)authentication;

        String password = authenticationToken.getCredentials().toString();
        String name = authenticationToken.getPrincipal().toString();//username
        //根据用户名获取用户信息
        UserDetails user = userVerify.loadUserByUsername(name);

        //对用户信息进行判断
        if(user == null) {
            throw new InternalAuthenticationServiceException("CustomUsernamePasswordAuthenticationProvider获取认证用户信息失败");
        }
        else if(
//                !this.passwordEncoder.matches((CharSequence) authenticationToken.getCredentials(), user.getPassword())
                !authenticationToken.getCredentials().equals(user.getPassword())
        ) {
            throw new BadCredentialsException("用户名或密码不正确");
        }

        // 认证完成后 将获取的用户数据 重新封装到认证对象中
        LoginAuthenticationToken authenticationToken1 = new LoginAuthenticationToken(name,password, user.getAuthorities());
//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
//        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

        // 设置一些详情信息
        authenticationToken1.setDetails(authenticationToken.getDetails());
        return authenticationToken1;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //指定该认证器对 认证对象进行认证
        return LoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
