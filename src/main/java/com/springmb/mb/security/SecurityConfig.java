package com.springmb.mb.security;//package com.zcf.appointment.common.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**security配置类
 * @author john
 * @date 2020/1/11 - 19:17
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MyUserProvider myUserProvider;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    private final LoginFilter loginFilter;
    public SecurityConfig(LoginFilter loginFilter) {
        this.loginFilter = loginFilter;
    }

    @Override
    // 用于构建认证 AuthenticationManager 认证
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(); 定义认证方式
        //  如果使用以下方法 会默认配置 DaoAuthenticationProvider 即账号 密码验证
        //传入自定义认证过程
//        userDetailsService(T userDetailsService)：传入自定义的 UserDetailsService 获取认证信息数据
        auth.authenticationProvider(myUserProvider);

//        /**
//         * 在内存中创建一个名为 "user" 的用户，密码为 "pwd"，拥有 "USER" 权限，密码使用BCryptPasswordEncoder加密
//         */
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("user").password(new BCryptPasswordEncoder().encode("pwd")).roles("USER");
//        /**
//         * 在内存中创建一个名为 "admin" 的用户，密码为 "pwd"，拥有 "USER" 和"ADMIN"权限
//         */
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("pwd")).roles("USER","ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/**/*.js");
        web.ignoring().antMatchers("/**/*.css");
    }

    @Override
    //配置springsecurity相关信息 用来配置拦截保护的请求
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable(); //关闭csrf拦截
        http.authorizeRequests().
                anyRequest().authenticated();
        http
                .formLogin()

                .loginPage("/webAdmin/login.html").permitAll()  //指定登录页面 允许所有用户访问登录页面.
                .loginProcessingUrl("/findAll/login")  //处理认证请求的路径
                .successHandler(myAuthenticationSuccessHandler) //指定自定义成功处理类
                .failureHandler(myAuthenticationFailHandler);   //指定自定义失败处理类

        http
                .logout()
                .logoutUrl("/my/logout")  // 指定注销路径
                .logoutSuccessUrl("/my/index") ;//指定成功注销后跳转到指定的页面
        //增加在FilterSecurityInterceptor前添加自定义的myFilterSecurityInterceptor
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class)
                //http.authorizeRequests() 方法中的自定义匹配
//                .antMatchers("/findAll/login").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")  //指定具有特定权限的用户才能访问特定目录，hasRole()方法指定用户权限，且不需前缀 “ROLE_“
//                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")//
                  //任何请求没匹配的都需要进行验证

                //The updated configuration specifies the location of the log in page  指定自定义登录页面

//                .defaultSuccessUrl("/webAdmin/index.html")//登录成功后默认跳转到"/hello"
//                .successForwardUrl("/webAdmin/index.html")//登录成功后默认跳转到"/hello"  貌似只支持post



//                .logoutSuccessHandler(logoutSuccessHandler)  //指定成功注销后处理类 如果使用了logoutSuccessHandler()的话， logoutSuccessUrl()就会失效
//                .invalidateHttpSession(true)  // httpSession是否有效时间，如果使用了 SecurityContextLogoutHandler，其将被覆盖
//                .addLogoutHandler(logoutHandler)  //在最后增加默认的注销处理类LogoutHandler
//                .deleteCookies(cookieNamesToClear);//指定注销成功后remove cookies
//        //增加在FilterSecurityInterceptor前添加自定义的myFilterSecurityInterceptor
//        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
        ;










//
//        ((AuthorizedUrl)((HttpSecurity)((FormLoginConfigurer)((HttpSecurity)((HttpSecurity)
//                http
//                        .csrf().disable())
//                .httpBasic().and())
//                .formLogin().loginPage("/login").loginProcessingUrl("/login")).and())
//                .authorizeRequests()
//                .anyRequest())
//                .authenticated();



        // 释放静态资源，指定资源拦截规则，指定自定义认证页面，指定退出认证配置，csrf配置
//        http
////                .csrf().disable()  //关闭csrf 认证
////
////                .headers()
////                .frameOptions().sameOrigin()
////                .httpStrictTransportSecurity().disable()//x-frame-options 关闭
////                .and()
////                //用于配置资源的访问权限
//                .authorizeRequests()
//                //指定用户可以访问的多个url资源
//
//
//                .antMatchers().permitAll()
////                .antMatchers("/**").hasAnyRole("ADMIN")
////                .antMatchers("/*.js","/*.css").permitAll()
////
////////                //任何请求都需要身份认证
////                  .anyRequest().authenticated()
//////                .antMatchers("/**").authenticated()
//                .and()
////
////                //用于配置登录验证逻辑相关的信息 登录页面、登录成功页面、登录请求处理路径等
//                .formLogin()
//                //用户未登录时，访问任何资源都转跳到该路径，即登录页面
//                .loginPage("/webAdmin/login.html")
////                //登录表单form中action的地址，也就是处理认证请求的路径
//               .loginProcessingUrl("/findAll/login")
//                .successForwardUrl("/webAdmin/index.html")
//                .successHandler(myAuthenticationSuccessHandler)
//                .failureHandler(myAuthenticationFailHandler)
//                .defaultSuccessUrl("/hello")//登录成功后默认跳转到"/hello"
//////                .failureForwardUrl("/failer.jsp")
////                .permitAll()
////                .and()
////                //开启cookie保存用户数据
////                .rememberMe()
////                //设置cookie有效期
////                .tokenValiditySeconds(60 * 60 * 24 * 7)
//                .and()
////
//                .logout()
//                //指定spring security拦截的注销url
//                .logoutUrl("/logout")
//                //用户退出后要被重定向的url
//                .logoutSuccessUrl("/login.jsp")
//                //默认为true,用户在退出后Http session失效
//                .invalidateHttpSession(true)
//                .permitAll()





        ;

    }

}
