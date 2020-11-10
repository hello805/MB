package com.springmb.mb.common.json;

public class ResponseJson {
    private long code;
    private String message;
    private Object data;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseJson(long code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    //返回成功
    public static ResponseJson success() {
        return new ResponseJson(Code.SUCCESS.getCode(), Code.SUCCESS.getMessage(), null);
    }

    public static ResponseJson success(Object data) {
        return new ResponseJson(Code.SUCCESS.getCode(), Code.SUCCESS.getMessage(), data);
    }

    public static ResponseJson success(String message) {
        return new ResponseJson(Code.SUCCESS.getCode(), message, null);
    }

    //返回失败
    public static ResponseJson failed() {
        return new ResponseJson(Code.FAILED.getCode(), Code.FAILED.getMessage(), null);
    }

    public static ResponseJson failed(String message) {
        return new ResponseJson(Code.FAILED.getCode(), message, null);
    }

//
//    /**
//     * 参数验证失败返回结果
//     */
//    public static <T> CommonResult<T> validateFailed() {
//        return failed(Code.VALIDATE_FAILED);
//    }
//
//    /**
//     * 参数验证失败返回结果
//     * @param message 提示信息
//     */
//    public static <T> CommonResult<T> validateFailed(String message) {
//        return new CommonResult<T>(Code.VALIDATE_FAILED.getCode(), message, null);
//    }
//
//    /**
//     * 未登录返回结果
//     */
//    public static <T> CommonResult<T> unauthorized(T data) {
//        return new CommonResult<T>(Code.UNAUTHORIZED.getCode(), Code.UNAUTHORIZED.getMessage(), data);
//    }
//
//    /**
//     * 未授权返回结果
//     */
//    public static <T> CommonResult<T> forbidden(T data) {
//        return new CommonResult<T>(Code.FORBIDDEN.getCode(), Code.FORBIDDEN.getMessage(), data);
//    }


}
