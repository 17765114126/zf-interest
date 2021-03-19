package com.example.springboot.model.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author <a href="mailto:cjw_0709@163.com">cjw</a>
 * @create 2019/7/2 14:11
 */
@Data
public class ModelAndButtonVo implements Serializable  {

    private static final long serialVersionUID = -5831463563478598330L;


    private List<ChilrenCmsModelVo> chilrenCmsModels;
}
