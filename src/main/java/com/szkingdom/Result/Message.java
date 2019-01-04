package com.szkingdom.Result;

/**
 * @author Lange
 * @date 2018-12-10 9:46
 */
public class Message {
    private int code;
    private String msg;

    /**
     * 100XX系统异常
     */
    public static Message RE_LOGIN = new Message(10000,"请重新登录");


    /**
     * 101XX 用户模块
     */
    public static Message DUPLICATE_USERNAME = new Message(10100,"用户名已存在");
    public static Message NO_PERMISSION = new Message(10100,"没有权限");


    public Message(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
