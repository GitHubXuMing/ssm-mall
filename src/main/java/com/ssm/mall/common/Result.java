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
    REGISTRY_VALID_SUCCESS(203,"注册名称验证成功，用户名或邮箱可用"),
    REGISTRY_SUCCESS(204,"注册成功"),
    EMAIL_ALREADY_EXIST(105,"EMAIL邮箱已存在"),
    REGISTRY_ILLEAGEL_ARGUMENT(106,"参数类型错误，只能选择用户名或EMAIL邮箱"),
    REGISTRY_ERROR(107,"注册失败"),
    CONFIG_READ_ERRO(108,"属性配置文件读取异常"),
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
