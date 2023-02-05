package com.gd.base.module.dict.service;

import com.gd.base.module.dict.mapper.DictMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DictService {

    @Resource
    private DictMapper dictMapper;

    /**
     * 根据字典类型和字典值获取字典标签
     * @param dictType
     * @param dictValue
     * @return
     */
    public String getLabelByTypeAndValue(String dictType, String dictValue) {
        return this.dictMapper.getLabelByTypeAndValue(dictType, dictValue);
    }

}
