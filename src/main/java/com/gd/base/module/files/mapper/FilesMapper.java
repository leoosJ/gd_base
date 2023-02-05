package com.gd.base.module.files.mapper;

import com.gd.base.base.crud.CrudMapper;
import com.gd.base.module.files.entity.Files;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilesMapper extends CrudMapper<Files> {

    Files getFileByMd5(String md5);

}
