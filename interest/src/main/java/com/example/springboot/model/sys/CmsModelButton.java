package com.example.springboot.model.sys;

import com.example.springboot.model.entity.CmsButton;
import lombok.Data;

import java.util.List;

@Data
public class CmsModelButton {
    private Integer id;

    private Integer pid;

    private Integer sort;

    private String name;

    private String url;

    private String imgUrl;

    List<CmsButton> cmsButtons;
}
