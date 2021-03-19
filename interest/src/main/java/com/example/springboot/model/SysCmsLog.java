package com.example.springboot.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysCmsLog
 * @Author zhaofu
 * @Date 2019/10/18
 * @Version V1.0
 **/
@Data
public class SysCmsLog implements Serializable {
    private Integer id;
    private String tblId;
    private Integer modelButtonCatrgoryId;
    private String loginName;
    private String loginMobile;
    private String operation;
    private String method;
    private String params;
    private Integer productId;
    private String qdNumber;
    private Date createDateTime;
    private Date updateTime;
    private Byte status;
    private Byte invalid;

}
