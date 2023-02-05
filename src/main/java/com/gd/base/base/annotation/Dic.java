package com.gd.base.base.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.*;


@JacksonAnnotationsInside
@JsonSerialize(using = DicSerializer.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dic {

    /**
     * 字典类型
     **/
    String type();

    /**
     * 字典赋值字段
     **/
    String fieldName()default "";

}
