package com.ssm.mall.common;
//使用枚举，实现统一异常处理
public enum Result {
    RESULT_SUCCESS(200,"success"),
    RESULT_ERROR(500,"error"),
    //用户模块-SUCCESS
    LOGIN_SUCCESS(201,"登录成功"),
    LOGOUT_SUCCESS(202,"注销成功"),
    REGISTRY_VALID_SUCCESS(203,"注册名称验证成功，用户名或邮箱可用"),
    REGISTRY_SUCCESS(204,"注册成功"),
    PASSWORD_RESET_SUCCESS(205,"密码重置成功"),
    MODIFY_PASSWORD_SUCCESS(206,"修改密码成功" ),
    //用户模块-ERROR
    USER_ALREADY_EXIST(102,"用户名已存在"),
    USER_NOT_EXISTS(103,"用户名不存在"),
    PASSWORD_ERROR(104,"密码错误"),
    EMAIL_ALREADY_EXIST(105,"EMAIL邮箱已存在"),
    REGISTRY_ILLEAGEL_ARGUMENT(106,"参数类型错误，只能选择用户名或EMAIL邮箱"),
    REGISTRY_ERROR(107,"注册失败"),
    CONFIG_READ_ERROR(108,"属性配置文件读取异常"),
    NEED_LOGIN(109,"请先登录"),
    NO_PASSWORD_RESET_QUESTION(110,"对不起，您没有设置重置密码的预设问题"),
    PASSWORD_RESET_ANSWER_ERROR(111,"预设问题答案不符，无法重置密码"),
    NEED_TOKEN(112,"参数错误，需要传递token令牌"),
    TOKEN_EXPIRE(113,"token令牌已过期"),
    TOKEN_ERROR(114,"token令牌无效，请重新获取"),
    PASSWORD_RESET_ERROR(115,"密码重置失败"),
    MODIFY_PASSWORD_ERROR(116,"密码修改失败"),
    ORIGIN_PASSWORD_ERROR(117,"原始密码校验失败"),
    USER_NOT_FOUND(118,"该用户没有找到"),
    MODIFY_USER_ERROR(119,"修改用户信息失败"),
    ADMIN_LOGIN_ERROR(120,"您没有管理员权限，无权登录管理系统"),
    //商品目录模块-SUCCESS
    CATEGORY_ADD_SUCCESS(207,"商品目录添加成功"),
    CATEGORY_UPDATE_SUCCESS(208,"商品目录更新成功"),
    //商品目录模块-ERROR
    CATEGORY_ADD_ERROR(121,"目录添加失败"),
    ILLEAGLE_ARGUMENT(122,"非法参数"),
    CATEGORY_UPDATE_ERROR(123,"商品目录更新失败"),
    CATEGORY_NO_CHILDREN(124,"该商品目录没有子目录"),
    CATEGORY_NO_FOUND(125,"该类商品不存在或已下架"),
    //3-商品管理模块
    INSERT_PRODUCT_SUCCESS(301,"商品插入成功"),
    UPDATE_PRODUCT_SUCCESS(302,"商品修改成功"),
    UPDATE_STATUS_SUCCESS(303,"更新商品状态（上架或下架）成功"),
    PRODUCT_DETAIL_SUCCESS(304,"获取商品信息成功"),
    UPLOAD_IMAGE_SUCCESS(305,"上传商品图片成功"),
    NEED_ADMIN_LOGIN(361,"请登录管理员账号后，进行操作"),
    INSERT_PRODUCT_ERROR(362,"商品插入失败"),
    UPDATE_PRODUCT_ERROR(362,"商品修改失败"),
    UPDATE_STATUS_ERROR(363,"更新商品状态（上架或下架）失败"),
    PRODUCT_NOT_FOUND(364,"商品已下架或删除"),
    PRODUCT_UPLOAD_IMAGE_ERROR(365,"上传商品图片失败");

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
