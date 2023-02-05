package com.gd.base.base.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础信息父类
 *
 * @author JLP
 * @date 2022-12-18
 */
@Data
public abstract class BaseEntity implements Serializable {

    protected static final long serialVersionUID = 1L;

    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";

    protected String id;
    protected Date createDate;
    protected Date updateDate;
    protected String createId;
    protected String updateId;
    protected String delFlag;
    protected boolean newRecord = false;

    public BaseEntity() {
        this.delFlag = DEL_FLAG_NORMAL;
    }

    public BaseEntity(String id) {
        this.id = id;
    }

    public boolean isNewRecord() {
        if (StringUtils.isEmpty(id)) {
            newRecord = true;
        }
        return newRecord;
    }
}
