package com.gd.base.common.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.auth0.jwt.JWT;
import com.gd.base.common.constant.GlobalConstant;
import com.gd.base.module.user.entity.User;
import com.gd.base.module.user.service.UserService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录用户工具类
 *
 */
public class UserUtil {

    private static UserService userService = SpringUtil.getBean(UserService.class);

    /**
     * 获取登录用户ID
     *
     * @return
     */
    public static String getUserId() {
        String token = getRequest().getHeader(GlobalConstant.AUTHORIZATION);// 从 http 请求头中取出 token
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    public static User getUser() {
        String token = getRequest().getHeader(GlobalConstant.AUTHORIZATION);// 从 http 请求头中取出 token
        String userId = JWT.decode(token).getAudience().get(0);
        User user = userService.get(userId);
        return user;
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

}
