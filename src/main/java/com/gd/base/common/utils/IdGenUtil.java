package com.gd.base.common.utils;

import com.gd.base.common.constant.GlobalConstant;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class IdGenUtil {

    /**
     * 主键生成
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * DM5加密
     *
     * @param plainText
     * @return
     */
    public static String md5(String plainText) {
        try{
            String md5 = DigestUtils.md5DigestAsHex(plainText.getBytes(GlobalConstant.CHARSET));
            return md5;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IdGenUtil.uuid();
    }
}
