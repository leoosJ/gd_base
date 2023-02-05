package com.gd.base.common.enumeration;

/**
 * 用户登录枚举
 *
 * @author JLP
 * @date 2022-12-19
 */
public enum UserStatusEnum {

    C1000("帐号已存在", 1000),
    C1001("帐号不存在", 1001);

    private String message;
    private Integer status;

    UserStatusEnum(String message, Integer status) {
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
