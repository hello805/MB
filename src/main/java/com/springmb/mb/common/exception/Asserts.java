package com.springmb.mb.common.exception;


/**
 * 断言处理类，用于抛出各种API异常
 * Created by macro on 2020/2/27.
 */
public class Asserts {
    //抛异常
    public static void fail(String message) {
        throw new ApiException(message);
    }

    //对条件为ture的处理-抛异常
    public static void fail(boolean bo, String error_msg) {
        if(bo) {
            fail(error_msg);
        }
    }
    public static void fail(boolean bo) {
        if(bo) {
            fail("错误");
        }
    }

    //对数据操作失败的处理-抛异常
    public static void fail(int line, String error_msg){
        if(line <= 0){
            fail(error_msg);
        }
    }
    public static void fail(int line){
        if(line <= 0){
            fail("影响行数：0");
        }
    }
}
