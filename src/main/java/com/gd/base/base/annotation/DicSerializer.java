package com.gd.base.base.annotation;

import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.gd.base.module.dict.service.DictService;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;

public class DicSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private DictService dictService = SpringUtil.getBean(DictService.class);

    private String type;
    private String fieldName;


    public DicSerializer(String type, String fieldName) {
        this.type = type;
        this.fieldName = fieldName;
    }

    public DicSerializer() {

    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(value);
        jsonGenerator.writeFieldName(fieldName);
        // 通过type和value获取label
        String label = this.dictService.getLabelByTypeAndValue(this.type, value);
        jsonGenerator.writeString(label);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            if (Objects.equals (beanProperty.getType ().getRawClass (), String.class)) {
                Dic t =beanProperty.getAnnotation (Dic.class);
                if (t != null) {
                    String beanFieldName = beanProperty.getName();
                    if(StringUtils.hasText(t.fieldName())){
                        beanFieldName = t.fieldName();
                    }
                    return new DicSerializer(t.type(), beanFieldName + "Name");
                }
            }
            return serializerProvider.findValueSerializer (beanProperty.getType (), beanProperty);
        }
        return serializerProvider.findNullValueSerializer (beanProperty);
    }

}
