package com.gd.base.module.login.controller;

import com.gd.base.base.entity.ResponseResult;
import com.gd.base.common.constant.GlobalConstant;
import com.gd.base.common.enumeration.LoginStatusEnum;
import com.gd.base.module.login.service.TokenService;
import com.gd.base.module.login.entity.Login;
import com.gd.base.module.user.entity.User;
import com.gd.base.module.user.service.UserService;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = { "用户登录" })
@RestController
public class LoginController {

    @Resource
    private TokenService tokenService;
    @Resource
    private UserService userService;

    @GetMapping(value = "/login")
    @ApiOperation(value = "登录")
    public ResponseResult<User> login(Login login, HttpServletResponse response, HttpServletRequest request) {
        ResponseResult<User> result = new ResponseResult<User>();

        // 根据帐号获取用户信息
        User user = this.userService.findUserByAccount(login.getAccount());

        // 帐号或密码错误
        if (user == null || !login.getPassword().equals(user.getPassword())) {
            result.setStatus(LoginStatusEnum.C1000.getStatus());
            result.setMessage(LoginStatusEnum.C1000.getMessage());
            return result;
        }

        // 生成token
        String token = this.tokenService.getToken(user);
        user.setToken(token);
        Cookie cookie = new Cookie(GlobalConstant.TOKEN, token);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setHeader(GlobalConstant.AUTHORIZATION, token);

        result.setData(user);
        return result;
    }

    @GetMapping("/generateCode")
    @ApiOperation(value = "生成验证码")
    public ResponseResult<Boolean> generateCode(HttpServletResponse response, HttpServletRequest request) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        HappyCaptcha.require(request, response)
                .style(CaptchaStyle.IMG)
                .build()
                .finish();
        result.setData(true);
        return result;
    }

    @PostMapping("/registry")
    @ApiOperation(value = "注册")
    public ResponseResult<User> registry(@RequestBody User user) {
        ResponseResult<User> result = this.userService.registry(user);
        return result;
    }

    @PostMapping("/forgetPassword")
    @ApiOperation(value = "忘记密码")
    public ResponseResult<Boolean> forgetPassword(@RequestBody User user) {
        ResponseResult<Boolean> result = this.userService.forgetPassword(user);
        return result;
    }

}
