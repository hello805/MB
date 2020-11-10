package com.springmb.mb.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmb.mb.common.json.ResponseJson;
//import com.springmb.mb.util.JwtTokenUtil;
import com.springmb.mb.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.springmb.mb.util.JwtTokenUtil.AUTH_HEADER_KEY;
import static com.springmb.mb.util.JwtTokenUtil.TOKEN_PREFIX;

@Slf4j
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    public static final String RETURN_TYPE = "html"; // 登录成功时，用来判断是返回json数据还是跳转html页面
    @Autowired
    private ObjectMapper objectMapper;
//    public CustomAuthenticationSuccessHandler() {
//        /**
//         * 指定默认登录成功请求的URL和是否总是使用默认登录成功请求的URL
//         * 注意：自定义的认证成功处理器，如果不指定，默认登录成功请求的URL是"/"
//         */
//        this.setDefaultTargetUrl(ConfigConstant.DEFAULT_LOGIN_SUCCESSFUL_REQUEST_URL);
//        this.setAlwaysUseDefaultTargetUrl(ConfigConstant.ALWAYS_USE_DEFAULT_LOGIN_SUCCESSFUL_REQUEST_URL);
//    }



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        //获取缓存中路径（跳转之前的路径）
//                RequestCache cache = new HttpSessionRequestCache();
//                SavedRequest savedRequest = cache.getRequest(request, response);
//                url = savedRequest.getRedirectUrl();
        //重定向到html页面
//        String url;
//        url="http://localhost:8080/webAdmin/index.html";
//        response.sendRedirect(url);
        log.info("④认证成功后调用 成功处理器 进行成功操作");
        LoginAuthenticationToken authentication1 = (LoginAuthenticationToken) authentication;
        String s = authentication1.getPrincipal().toString();


        //或者返回json数据
        String admin = JwtTokenUtil.createJWT(s, s, "admin");
        response.setHeader(AUTH_HEADER_KEY,TOKEN_PREFIX+" "+admin);
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString( ResponseJson.success("")));

    }
}
