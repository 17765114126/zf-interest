package com.example.springboot.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: yiqian-cms
 * @description: 省市区
 * @create: 2020-12-09 11:40
 */
@Data
public class MallRegion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 京东父级区域id
     */
    private Integer jdPid;

    /**
     * 地址名称
     */
    private String name;

    /**
     * 区域级别
     */
    private Integer grade;

    /**
     * 京东区域id
     */
    private Integer jdId;

    /**
     * 苏宁区域id
     */
    private String snId;

    /**
     * 苏宁父级区域id
     */
    private String snPid;

    /**
     * 杭州女装网区域id
     */
    private String hznzwId;

    /**
     * 杭州女装网父区域id
     */
    private String hznzwPid;

    /**
     * invalid
     */
    private Integer invalid;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * update_time
     */
    private Date updateTime;

    /**
     * yqh_id
     */
    private Integer yqhId;

    /**
     * yqh_pid
     */
    private Integer yqhPid;
}
