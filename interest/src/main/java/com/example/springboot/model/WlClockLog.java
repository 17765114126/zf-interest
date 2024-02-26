package com.example.springboot.model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @打卡记录表
 *
 * @author zf
 * @since 2023-02-23
 */
@Data
public class WlClockLog {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 学生/老人id
     */
    private Long studentOrElderId;
    /**
     * 姓名
     */
    private String name;

    /**
     * 学生卡号
     */
    private String studentCard;
    /**
     * 打卡点位id
     */
    private Long clockPointId;

    /**
     * 点位类别，1-门禁，2-物联相机
     */
    private Integer clockStatus;

    /**
     * 1:学生 2：老人
     */
    private Integer type;

    /**
     * 异常原因
     */
    private String errorCause;

    /**
     * 打卡图片
     */
    private String clockImg;

    /**
     * 事件ID
     */
    private String eventId;

    /**
     * 打卡时间
     */
    private LocalDateTime eventTime;

    /**
     * 进出类型(1：进 0：出 -1:未知）
     */
    private Integer inAndOutType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
