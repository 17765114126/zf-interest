package com.example.springboot.model.req.sys;

import com.example.springboot.model.dto.CmsUserDto;
import com.example.springboot.model.entity.CmsUser;
import com.example.springboot.model.form.Paging;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author jzl
 * @create 2019-07-02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmsUserReq extends Paging<CmsUser> {

    private Long id;
    private String username;
    private String password;
    private String mobile;
    private String roleIds;
    private Integer status;
    private Date createTime;
    private Integer invalid;

    private String queryParam;
    private String roleParam;

    public CmsUserDto getDto() {
        CmsUserDto cmsUserDto = new CmsUserDto();
        cmsUserDto.setId(id);
        cmsUserDto.setUsername(username);
        cmsUserDto.setMobile(mobile);
        cmsUserDto.setRoleIds(roleIds);
        cmsUserDto.setPassword(password);
        return cmsUserDto;
    }
}
