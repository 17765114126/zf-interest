package com.example.application.model.dto;

import com.example.application.model.entity.CmsUser;
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
