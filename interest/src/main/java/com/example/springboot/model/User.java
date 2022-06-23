package com.example.springboot.model;

import com.baomidou.mybatisplus.annotation.Version;
import com.example.springboot.config.annotation.Encrypt;
import com.example.springboot.model.entity.MallRegion;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName User
 * @Author zhaofu
 * @Date 2019/10/26
 * @Version V1.0
 * @Description: 注解@Component("user")用来装配你的Bean
 * @controller入参处应该要加上
 * @Valid注解 , 否则不会验证!
 **/
@Component("user")
@Data
public class User {
    @Value("1")
    private Long id;

    @Null(message = "必须为null")
    private Integer test1;

    @AssertTrue(message = "必须为true")
    @AssertFalse(message = "必须为false")
    private Boolean test2;

    @Min(value = 1, message = "最小值为1")
    @Max(value = 88, message = "最大值为88")
    @Range (min = 1,max = 88,message = "范围为1至888")//被注释的元素必须在合适的范围内
    private Integer test3;

    @DecimalMin(value = "0.1", message = "最小值0.1元")
    @DecimalMax(value = "10000.00", message = "最大值为10000.00")
    private BigDecimal test4;

    @Size(min = 10,max = 30,message = "字符串长度要求20到30之间。")
    @NotEmpty(message = "不能为空")
    @Length(max = 11, message = "不能超过11个字符")
    private String test5;

//      @Digits 被注释的元素必须是一个数字, 其值必须在可接受的范围内
    @Past(message = "必须为过去日期")
    @Future(message = "必须为将来日期")
    private Date test6;

//    @Pattern(regexp = "^(1[3-9]\\d{9}$)", message = "请输入11位有效手机号")
    @NotBlank(message = "不能为null")
    @Email(message = "邮箱格式错误")
    private String email;


    @Encrypt//自定义加密
    private String userName;
    private MallRegion address;
    @Value("note_1")
    private String note;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)//字段为null此字段不反回
    private String invalid;


    /**
     * mybatis 乐观锁   
     * 加一个注解 @Version
     * */
    @Version
    private Integer version=0;

}
