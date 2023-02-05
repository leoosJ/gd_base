package com.gd.base.module.login.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gd.base.module.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    //设置过期时间
    private static final long EXPIRE_TIME = 60 * 60 * 1000;

    /**
     * 获取用户登录token
     *
     * @author JLP
     * @param user
     * @return
     * @date 2022-12-18
     */
    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + EXPIRE_TIME;
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(user.getId()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));

        return token;
    }

}
