package com.gd.base.module.login.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录
 */
@Data
public class Login {

    @ApiModelProperty(value = "帐号", position = 1)
    private String account;
    @ApiModelProperty(value = "密码", position = 2)
    private String password;
    @ApiModelProperty(value = "验证码", position = 3)
    private String verifyCode;

}
