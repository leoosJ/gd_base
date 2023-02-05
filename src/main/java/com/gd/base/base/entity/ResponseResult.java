package com.gd.base.base.entity;

import com.gd.base.common.constant.GlobalConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回值封装
 *
 * @author JLP
 * @date 2022-12-17
 */
@Data
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status = GlobalConstant.SUCCESS_CODE;
    private String message = GlobalConstant.SUCCESS_MESSAGE;
    private T data;

}
