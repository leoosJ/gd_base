package com.gd.base.module.user.controller;

import com.gd.base.base.entity.ResponseResult;
import com.gd.base.base.interceptor.UserLoginToken;
import com.gd.base.base.page.Page;
import com.gd.base.module.user.entity.User;
import com.gd.base.module.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = { "用户管理" })
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @UserLoginToken
    @GetMapping(value = "/auth/user/get/{id}")
    @ApiOperation(value = "查询用户信息")
    public ResponseResult<User> get(@PathVariable(value = "id") String id) {
        ResponseResult<User> result = new ResponseResult<User>();
        User user = this.userService.get(id);
        result.setData(user);
        return result;
    }

    @UserLoginToken
    @PostMapping(value = "/auth/user/list")
    @ApiOperation(value = "查询用户分页列表")
    public ResponseResult<Page<User>> list(@RequestBody User user, @RequestParam(value = "pageNum", required = true) int pageNum, @RequestParam(value = "pageSize", required = true) int pageSize) {
        ResponseResult<Page<User>> result = new ResponseResult<Page<User>>();
        Page<User> page = this.userService.findPage(new Page<User>(pageNum, pageSize), user);
        result.setData(page);
        return result;
    }

    @UserLoginToken
    @PostMapping(value = "/auth/user/save")
    @ApiOperation(value = "保存用户")
    public ResponseResult<Boolean> save(@RequestBody User user) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.userService.save(user);
        result.setData(true);
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "/auth/user/delete/{id}")
    @ApiOperation(value = "物理删除用户")
    public ResponseResult<Boolean> delete(@PathVariable(value = "id") String id) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.userService.delete(id);
        result.setData(true);
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "/auth/user/deleteByLogic/{id}")
    @ApiOperation(value = "逻辑删除用户")
    public ResponseResult<Boolean> deleteByLogic(@PathVariable(value = "id") String id) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.userService.deleteByLogic(id);
        result.setData(true);
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "/auth/user/deleteBatch")
    @ApiOperation(value = "批量删除用户")
    public ResponseResult<Boolean> deleteBatch(@RequestParam(value = "ids", required = true) String ids) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        if (StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            for (String id: idArr) {
                this.userService.deleteByLogic(id);
            }
        }
        result.setData(true);
        return result;
    }

}
