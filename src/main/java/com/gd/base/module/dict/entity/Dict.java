package com.gd.base.module.dict.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Dict {

    @ApiModelProperty(value = "字典类型", position = 1)
    private String dictType;
    @ApiModelProperty(value = "字典值", position = 2)
    private String dictValue;
    @ApiModelProperty(value = "字典标签", position = 3)
    private String dictLabel;

}
