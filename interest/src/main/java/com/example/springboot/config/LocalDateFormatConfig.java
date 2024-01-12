package com.example.springboot.config;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Configuration
public class LocalDateFormatConfig {


//    /**
//     * 所有反参LocalDate类型 格式化
//     * */
//    @Bean
//    @Primary
//    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
//        //日期转字符串
//        return builder -> builder
//                .serializerByType(LocalDate.class,new LocalDateSerializer(DatePattern.NORM_DATE_FORMATTER))
//                .serializerByType(LocalDateTime.class,new LocalDateTimeSerializer(DatePattern.NORM_DATETIME_FORMATTER))
//                .serializerByType(LocalTime.class,new LocalTimeSerializer(DatePattern.NORM_TIME_FORMATTER))
//                //字符串转日期
//                .deserializerByType(LocalDate.class,new LocalDateDeserializer(DatePattern.NORM_DATE_FORMATTER))
//                .deserializerByType(LocalDateTime.class,new LocalDateTimeDeserializer(DatePattern.NORM_DATETIME_FORMATTER))
//                .deserializerByType(LocalTime.class,new LocalTimeDeserializer(DatePattern.NORM_TIME_FORMATTER));
//    }
}