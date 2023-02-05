package com.gd.base.module.user.entity;

import com.gd.base.base.annotation.Dic;
import com.gd.base.base.entity.BaseEntity;
import com.gd.base.common.constant.DictConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User extends BaseEntity {

    @ApiModelProperty(value = "姓名", position = 1)
    private String userName;
    @ApiModelProperty(value = "帐号", position = 2)
    private String account;
    @ApiModelProperty(value = "密码", position = 3)
    private String password;
    @ApiModelProperty(value = "性别", position = 4)
    @Dic(type = DictConstant.SEX)
    private String sex;
    @ApiModelProperty(value = "手机号", position = 5)
    private String mobile;
    @ApiModelProperty(value = "证件号", position = 6)
    private String idCard;
    @ApiModelProperty(value = "邮箱", position = 7)
    private String email;
    @ApiModelProperty(value = "头像", position = 8)
    private String userPhoto;
    @ApiModelProperty(value = "详细地址", position = 9)
    private String address;
    @ApiModelProperty(value = "用户类型 1：管理员 2：普通用户", position = 10)
    @Dic(type = DictConstant.USER_TYPE)
    private String userType;
    @ApiModelProperty(value = "token", position = 11)
    private String token;

}
 
