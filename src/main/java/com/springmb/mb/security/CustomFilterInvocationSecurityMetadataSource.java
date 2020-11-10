package com.springmb.mb.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 自定义的资源（url）权限（角色）数据获取类
 */
@Slf4j
@Component("customFilterInvocationSecurityMetadataSource")
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 每个资源（url）所需要的权限（角色）集合
     */
    private static HashMap<String, Collection<ConfigAttribute>> map =null;

    /**
     * 获取每个资源（url）所需要的权限（角色）集合
     * 这里应该从数据库中动态查询，这里为了方便而直接创建
     */
    private void getResourcePermission(){
        map = new HashMap<>();

        /**
         * 创建两个权限：ROLE_ADMIN 和 ROLE_EMPLOYEE
         */
        ConfigAttribute adminRole = new SecurityConfig("ROLE_ADMIN");
        ConfigAttribute employeeRole = new SecurityConfig("ROLE_EMPLOYEE");

        /**
         * 创建两个权限集合
         */
        List<ConfigAttribute> adminUrlRoles = new ArrayList<>();
        adminUrlRoles.add(adminRole);
        List<ConfigAttribute> employeeUrlRoles = new ArrayList<>();
        employeeUrlRoles.add(employeeRole);

        /**
         * 设置资源（url）所需要的权限（角色）集合
         */
        map.put("/webAdmin/pages/agency/extensionAgent.html", adminUrlRoles);
//        map.put("/login.html", employeeUrlRoles);
        map.put("/toUser", null);
        map.put("/toAbout", null);
        map.put("/toHome", null);
        map.put("/getPrincipal", null);
        map.put("/getUserDetails", null);
    }

    /**
     * 获取用户请求的某个具体的资源（url）所需要的权限（角色）集合
     * @param object 包含了用户请求的request信息
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(map ==null) {
            getResourcePermission();
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
//        log.info("获取该路径所需权限：{}",request);
        /**
         * 遍历每个资源（url），如果与用户请求的资源（url）匹配，则返回该资源（url）所需要的权限（角色）集合，
         * 如果全都不匹配，则表示用户请求的资源（url)不需要权限（角色）即可访问
         */
        Iterator<String> iter = map.keySet().iterator();
        while(iter.hasNext()) {
            String url = iter.next();
            if(new AntPathRequestMatcher(url).matches(request)) {
                return map.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
