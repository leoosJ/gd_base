package com.gd.base.common.enumeration;

/**
 * 用户登录枚举
 *
 * @author JLP
 * @date 2022-12-19
 */
public enum LoginStatusEnum {

    C1000("帐号或密码错误", 1000),
    C1001("验证码错误", 1001),
    C1002("请输入验证码", 1002),
    C1003("需要验证码", 1003),
    // 1004：帐号密码错误且需要验证码验证
    C1004("帐号或密码错误", 1004);

    private String message;
    private Integer status;

    LoginStatusEnum(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status + "_" + this.message;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getStatus() {
        return this.status;
    }

}
