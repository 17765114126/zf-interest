package com.example.springboot.model.req;

import com.example.springboot.config.annotation.Trimmed;
import com.example.springboot.model.form.Paging;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName UserReq
 * @Author zhaofu
 * @Date 2020/11/5
 * @Version V1.0
 **/
@Data
public class UserReq extends Paging {


    private Long id;
    @Trimmed/**清除字符串前后的空格*/
    @NotBlank(message = "请填写用户名")
    private String userName;

    private String note;

    private String date;

    private String email;

    private String invalid;
}
