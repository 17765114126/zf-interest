package com.example.springboot.model.dto;

import com.example.springboot.model.entity.CmsUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jzl
 * @create 2019-07-02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmsUserDto extends CmsUser {

    private String queryParam;
    private String roleIds;
}
