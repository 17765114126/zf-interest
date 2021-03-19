package com.example.springboot.model.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CmsExportVO {
    private Long id;

    /**
     * 导出唯一编码
     */

    private String exportCode;

    /**
     * 文件名称
     */

    private String title;

    /**
     * 导出类型 (1:用户数据导出)
     */

    private Integer cType;

    /**
     * 文件类型 (1:excel) (2:csv)
     */

    private Integer eType;

    /**
     * 导出人id
     */

    private Long userId;

    /**
     * 导出人姓名
     */

    private String userName;

    /**
     * 导出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date exportTime;

    /**
     * 0:导出中 1:已完成
     */

    private Integer isExport;

    /**
     * 进度
     */
    private Integer schedule = 100;
}
