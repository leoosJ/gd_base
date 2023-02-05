package com.gd.base.base.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gd.base.common.constant.GlobalConstant;
import com.gd.base.common.enumeration.TokenStatusEnum;
import com.gd.base.module.user.entity.User;
import com.gd.base.module.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 拦截器
 *
 * @author  JLP
 * @date 2022-12-18
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(GlobalConstant.AUTHORIZATION);

        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method = handlerMethod.getMethod();
        //检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (StringUtils.isEmpty(token)) {
                    httpServletResponse.sendError(TokenStatusEnum.C201.getStatus(), TokenStatusEnum.C201.getMessage());
                    throw new RuntimeException("暂无token");

                }

                DecodedJWT decodeToken = JWT.decode(token);
                boolean expire = decodeToken.getExpiresAt().before(new Date());

                if (expire) {
                    httpServletResponse.sendError(TokenStatusEnum.C202.getStatus(), TokenStatusEnum.C202.getMessage());
                    throw new RuntimeException("token过期");
                }

                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    httpServletResponse.sendError(TokenStatusEnum.C203.getStatus(), TokenStatusEnum.C203.getMessage());
                    throw new RuntimeException("无效token");
                }
                User user = this.userService.get(userId);
                if (user == null) {
                    httpServletResponse.sendError(TokenStatusEnum.C204.getStatus(), TokenStatusEnum.C204.getMessage());
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    httpServletResponse.sendError(TokenStatusEnum.C205.getStatus(), TokenStatusEnum.C205.getMessage());
                    throw new RuntimeException("token验证失败");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
