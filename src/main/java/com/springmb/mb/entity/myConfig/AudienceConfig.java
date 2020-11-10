package com.springmb.mb.entity.myConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class AudienceConfig {

    private String clientId;//接受对象
    private String base64Secret;//秘钥
    private String name;//签发对象
    private int expiresSecond; //过期时间

}