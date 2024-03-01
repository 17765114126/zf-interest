package com.example.application.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @
 *
 * @author zf
 * @since 2021-04-03
 */
@Data
  public class WallHaven implements Serializable {

    private static final long serialVersionUID = 1L;

  @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 来源：0：https://wallhaven.cc/ 
     */
    private Integer source;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 逻辑删除
     */
    private Integer invalid;


}
