package com.springmb.mb.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.springmb.mb.common.json.ResponseJson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常拦截
 */
@ControllerAdvice // 可指定包前缀，例如：(basePackages = "com.pj.controller.admin")
public class GlobalException {
    // 全局异常拦截
    @ResponseBody
    @ExceptionHandler
    public ResponseJson handlerException(Exception e) {

        // 打印堆栈，以供调试
        e.printStackTrace();

        //如果属于自定义异常
        if (e instanceof ApiException) {
            return ResponseJson.failed(e.getMessage());
        }
        //其他异常返回失败
        return ResponseJson.failed(e.getMessage());


        // 输出到前台
//			response.setContentType("application/json; charset=utf-8"); // http说明，我要返回JSON对象
//			response.getWriter().print(new ObjectMapper().writeValueAsString(aj));
    }
}

