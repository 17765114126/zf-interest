package com.example.springboot.model.sys;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

public class SuUserShopKeeperExportExcelVO  {
    @Excel(name = "申请人手机号",width= 20,height = 15)
    private String mobile;
    @Excel(name = "订单号",width= 20,height = 15)
    private String orderNo;
    @Excel(name = "状态",replace = {"待审核_1", "审核不通过_2","审核通过_3"}   ,width= 20,height = 15)
    private String status;
    @Excel(name = "店长升级类型",replace = {"付费升级_1", "免费升级_2"},width= 20,height = 15)
    private Integer cType;
    @Excel(name = "添加时间",format = "yyyy-MM-dd HH:mm:ss",width= 20,height = 15)
    private Date createDateTime;
    @Excel(name = "审核时间",format = "yyyy-MM-dd HH:mm:ss",width= 20,height = 15)
    private Date auditTime;
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getcType() {
        return cType;
    }

    public void setcType(Integer cType) {
        this.cType = cType;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    @Override
    public String toString() {
        return "SuUserShopKeeperExportExcelVO{" +
                "mobile='" + mobile + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", status=" + status +
                ", cType=" + cType +
                ", createDateTime=" + createDateTime +
                ", auditTime=" + auditTime +
                '}';
    }

}
