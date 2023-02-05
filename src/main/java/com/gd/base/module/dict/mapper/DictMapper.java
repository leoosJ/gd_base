package com.gd.base.module.dict.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DictMapper {

    /**
     * 根据字典类型和字典值获取字典标签
     * @param dictType
     * @param dictValue
     * @return
     */
    String getLabelByTypeAndValue(@Param(value = "dictType") String dictType, @Param(value = "dictValue") String dictValue);

}
