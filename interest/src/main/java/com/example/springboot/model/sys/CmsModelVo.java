package com.example.springboot.model.sys;

import com.example.springboot.model.entity.CmsButton;
import com.example.springboot.model.entity.CmsModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2019/7/1.
 */
@Data
public class CmsModelVo {
    private Integer id;

    private Integer pid;

    private Integer order;

    private String name;

    private String url;

    private Integer sort;

    private String imgUrl;

    private String sysTag;

    private Byte status;

    private Date createDateTime;

    private Long permissionId;

    List<CmsModel> cmsModel;

    List<CmsModelButton> cmsModels;

    List<CmsButton> cmsButton;

}
