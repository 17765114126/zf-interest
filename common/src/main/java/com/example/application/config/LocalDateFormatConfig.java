package com.example.application.config;

import org.springframework.context.annotation.Configuration;


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