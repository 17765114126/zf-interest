package Test;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @Description 导入
 * @Author zf
 * @Date 2019/10/29 16:17
 * @Version 1.0
 */
@Data
public class ImportExcelDTO {
//    @Excel(name = "退款时间", width = 25, format = "yyyy-MM-dd HH:mm:ss")
//    private String time;


//    @Excel(name = "职业", width = 30)
//    private String cart;
//
    @Excel(name = "家长姓名", width = 30)
    private String name;

    @Excel(name = "电话", width = 30)
    private String phone;


    @Excel(name = "幼儿姓名", width = 30)
    private String CName;
//
//    @Excel(name = "证件号码", width = 200)
//    private String cartNum;
//
//    @Excel(name = "工作单位", width = 200)
//    private String workplace;
//
//    @Excel(name = "所属小区", width = 200)
//    private String address;
//
//    @Excel(name = "account_id", width = 200)
//    private String accountId;
//
//    @Excel(name = "biz_type", width = 200)
//    private String bizType;
//
//    @Excel(name = "biz_order_pay_id", width = 200)
//    private String bizOrderPayId;
//
//    @Excel(name = "out_pay_id", width = 200)
//    private String outPayId;
//
//    @Excel(name = "pay_amt", width = 200)
//    private String payAmt;
//
//    @Excel(name = "sub_order_type", width = 200)
//    private String subOrderType;
//
//    @Excel(name = "pay_way", width = 200)
//    private String payWay;
//
//    @Excel(name = "podId", width = 200)
//    private String podId;
//
//    @Excel(name = "podOpId", width = 200)
//    private String podOpId;
//
//    @Excel(name = "out_refund_id", width = 200)
//    private String outRefundId;
//
//    @Excel(name = "refund_create_time", width = 200)
//    private String refundCreateTime;
//
//
//    @Excel(name = "refund_time", width = 200)
//    private String refundTime;
//
//    @Excel(name = "porId", width = 200)
//    private String porId;


}
