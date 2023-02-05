package com.gd.base;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement // 开启事务管理
@EnableSpringUtil
@EnableSwagger2
public class GdBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdBaseApplication.class, args);
    }

}
