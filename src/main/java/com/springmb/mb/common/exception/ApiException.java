package com.springmb.mb.common.exception;


/**
 * 自定义API异常
 * Created by macro on 2020/2/27.
 */
public class ApiException extends RuntimeException {

    //自定义异常的构造
    /*无参构造函数*/
    public ApiException(){
        super();
    }
    //用详细信息指定一个异常
    public ApiException(String message){
        super(message);
    }
    //用指定原因构造一个新的异常
    public ApiException(Throwable cause) {
        super(cause);
    }
    //用指定的详细信息和原因构造一个新的异常
    public ApiException(String message, Throwable cause){
        super(message,cause);
    }

    //抛异常
    public static ApiException throwException(String message){
       throw  new ApiException(message);
    }




//    //对条件为ture的处理-抛异常
//    public static void getBy(boolean bo, String error_msg) {
//        if(bo) {
//            throwException(error_msg);
//        }
//    }
//    public static void getBy(boolean bo) {
//        if(bo) {
//            throwException("错误");
//        }
//    }
//
//    //对数据操作失败的处理-抛异常
//    public static void getByLine(int line, String error_msg){
//        if(line <= 0){
//            throwException(error_msg);
//        }
//    }
//    public static void getByLine(int line){
//        if(line <= 0){
//            throwException("影响行数：0");
//        }
//    }



}
