package com.example.springboot.model;

import com.example.springboot.config.annotation.Encrypt;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName User
 * @Author zhaofu
 * @Date 2019/10/26
 * @Version V1.0
 * @Description: 注解@Component("user")用来装配你的Bean
 **/
@Component("user")
@Data
public class User {
    @Value("1")
    @NotNull
    private Long id;
    @Encrypt
    private String userName;
    @Value("note_1")
    private String note;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    @Email(message = "邮箱格式错误")
    private String email;
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)//字段为null此字段不反悔
    private String invalid;
}
