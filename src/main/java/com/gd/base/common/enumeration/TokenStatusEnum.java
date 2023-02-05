package com.gd.base.common.enumeration;

/**
 * 用户登录枚举
 *
 * @author JLP
 * @date 2022-12-19
 */
public enum TokenStatusEnum {

    C201("暂无token", 201),
    C202("token过期", 202),
    C203("无效token", 203),
    C204("用户不存在，请重新登录", 204),
    C205("token验证失败", 205);

    private String message;
    private Integer status;

    TokenStatusEnum(String message, Integer status) {
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
