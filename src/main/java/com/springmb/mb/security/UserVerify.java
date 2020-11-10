package com.springmb.mb.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springmb.mb.entity.MbUser;
import com.springmb.mb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserVerify implements  UserService{

@Autowired
private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //根据用户名 获取对应用户信息（账号，密码，权限）
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();

        MbUser user = userMapper.selectOne(new QueryWrapper<MbUser>().eq("username",userName));
        if (user.getUsername().equals("1")){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
            simpleGrantedAuthorities.add(simpleGrantedAuthority);
        }
        return new User(user.getUsername(), user.getPassword(),simpleGrantedAuthorities);
    }


    //根据用户手机号 获取验证码
    //获取用户权限信息
    //返回
}
