package com.example.springboot.model.sys;

import com.example.springboot.model.entity.CmsUser;
import com.example.springboot.utils.CopyUtil;
import com.example.springboot.utils.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author jzl
 * @create 2019-07-02
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CmsUserVo {

    private Long id;
    private String username;
    private String password;
    private String mobile;
    private String roleIds;
    private String roleNames;
    private Integer status;
    @JsonFormat(pattern = DateUtil.DEFAULT_TIME, timezone = "GMT+8")
    private Date createTime;
    private Integer invalid;

    public CmsUserVo(CmsUser cmsUser) {
        CopyUtil.copyObject(cmsUser, this);
    }
}
