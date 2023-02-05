package com.gd.base.module.user.service;

import com.gd.base.base.crud.CrudService;
import com.gd.base.base.entity.ResponseResult;
import com.gd.base.common.constant.GlobalConstant;
import com.gd.base.common.enumeration.UserStatusEnum;
import com.gd.base.common.utils.IdGenUtil;
import com.gd.base.module.user.entity.User;
import com.gd.base.module.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService extends CrudService<UserMapper, User> {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据帐号查询用户信息
     *
     * @param account
     * @return
     */
    public User findUserByAccount(String account) {
        return this.userMapper.findUserByAccount(account);
    }

    public ResponseResult<User> registry(User user) {
        ResponseResult<User> result = new ResponseResult<User>();

        // 根据帐号获取用户信息
        User dataUser = this.userMapper.findUserByAccount(user.getAccount());
        // 帐号已存在，无法注册
        if (dataUser != null) {
            result.setData(null);
            result.setStatus(UserStatusEnum.C1000.getStatus());
            result.setMessage(UserStatusEnum.C1000.getMessage());
            return result;
        }

        // 注册用户
        user.setId(IdGenUtil.uuid());
        user.setCreateId(user.getId());
        user.setUpdateId(user.getId());
        user.setNewRecord(true);
        this.save(user);

        result.setData(user);
        return result;

    }

    /**
     * 修改密码
     *
     * @author JLP
     * @param user
     * @return\
     * @date 2022-12-21
     */
    public ResponseResult<Boolean> forgetPassword(User user) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();

        try {
            // 根据帐号获取用户信息
            User dataUser = this.userMapper.findUserByAccount(user.getAccount());
            if (dataUser == null) {
                // 帐号不存在
                result.setData(false);
                result.setStatus(UserStatusEnum.C1001.getStatus());
                result.setMessage(UserStatusEnum.C1001.getMessage());
                return result;
            } else {
                // 更新帐号密码
                dataUser.setPassword(user.getPassword());
                this.save(dataUser);
                result.setData(true);
            }
        } catch (Exception e) {
            result.setData(false);
            result.setStatus(GlobalConstant.FAIL_CODE);
            result.setMessage(GlobalConstant.FAIL_MESSAGE);
            return result;
        }

        return result;
    }

}
