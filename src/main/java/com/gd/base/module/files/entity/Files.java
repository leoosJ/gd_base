package com.gd.base.module.files.entity;

import com.gd.base.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Files extends BaseEntity {

    @ApiModelProperty(value = "文件名", position = 1)
    private String name;
    @ApiModelProperty(value = "文件类型", position = 2)
    private String type;
    @ApiModelProperty(value = "文件大小", position = 3)
    private long size;
    @ApiModelProperty(value = "文件路径", position = 4)
    private String url;
    @ApiModelProperty(value = "文件加密", position = 5)
    private String md5;

}
 
