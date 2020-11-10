package com.springmb.mb.util;

import cn.hutool.crypto.SecureUtil;
import com.springmb.mb.entity.myConfig.MyConfig;
import com.springmb.mb.entity.myConfig.AudienceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 有关当前项目的一些工具方法 
 * @author kong
 *
 */
@Component
public class SystemUtils {

	// ===================================== 一些二次封装的方法 ===================================================

	/** 返回md5加密后的密码，根据当前配置的salt
	 *   格式为： md5(salt + userid + password) 
	 */ 
	public static String getPasswordMD5(long user_id, String password) {
		return SecureUtil.md5(config.getMd5_salt() + user_id + password).toUpperCase();
	}
	public static AudienceConfig getAudienceConfig(){
		return audienceConfig;
	}
	// ===================================== yml自定义配置信息 ===================================================
	
	public static MyConfig config;
	public static AudienceConfig audienceConfig;
	@Autowired
	public void setMyConfig(MyConfig config) {
		SystemUtils.config = config;
	}
	@Autowired
	public void setAudience(AudienceConfig audienceConfig){
		SystemUtils.audienceConfig = audienceConfig;
	}

	
}
