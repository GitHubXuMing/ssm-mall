package com.ssm.mall.common;
//使用枚举，实现统一异常处理
public enum Result {
    RESULT_SUCCESS(200,"success"),
    RESULT_ERROR(500,"error"),
    USER_ALREADY_EXIST(102,"用户名已存在"),
    USER_NOT_EXISTS(103,"用户名不存在"),
    PASSWORD_ERROR(104,"密码错误"),
    LOGIN_SUCCESS(201,"登录成功"),
    LOGOUT_SUCCESS(202,"注销成功"),
    ;

    private final int status;
    private final String msg;

    Result(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
