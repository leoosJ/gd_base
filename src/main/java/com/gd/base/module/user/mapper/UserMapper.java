package com.gd.base.module.user.mapper;

import com.gd.base.base.crud.CrudMapper;
import com.gd.base.module.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends CrudMapper<User> {

    User findUserByAccount(String account);

}
