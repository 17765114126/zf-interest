package Test;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zf
 * @支付订单子表
 * @since 2022-03-22
 */
@Data
public class PayOrderDetailsDO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Excel(name = "id")
    private Long id;

    @Excel(name = "biz_order_id")
    private Long bizOrderId;

    @Excel(name = "biz_order_pay_id")
    private Long bizOrderPayId;

    @Excel(name = "org_order_id")
    private String orgOrderId;

    /**
     * 三方支付id
     */
    @Excel(name = "out_pay_id")
    private String outPayId;

    /**
     * 业务类型 B01：整车购 E01:服务包购买 E02:服务商品购买 F01:OTA商城购买
     */
    @Excel(name = "biz_type")
    private String bizType;

    /**
     * 子订单类型 1:意向金 2:定金 3:意转定4：尾款
     */
    @Excel(name = "sub_order_type")
    private Integer subOrderType;

    /**
     * 付款账户
     */
    @Excel(name = "account_no")
    private String accountNo;

    /**
     * 标题
     */
    @Excel(name = "title")
    private String title;

    /**
     * 支付内容
     */
    @Excel(name = "content")
    private String content;

    /**
     * 订单金额
     */
    @Excel(name = "pay_amt")
    private BigDecimal payAmt;

    /**
     * 支付方式 1:微信 2:支付宝 3:聚合 4:支付中心
     */
    @Excel(name = "pay_way")
    private Integer payWay;

    /**
     * 支付产品:1-app 2-小程序 3-H5 4-PC端  5-百度小程序
     */
    @Excel(name = "pay_product")
    private Integer payProduct;

    /**
     * 支付状态
     */
    @Excel(name = "pay_status")
    private Integer payStatus;

    /**
     * 支付返回信息
     */
    @Excel(name = "pay_ret_msg")
    private String payRetMsg;

    @Excel(name = "pay_create_time")
    private String payCreateTime;

    @Excel(name = "pay_time")
    private String payTime;

    /**
     * 扩展属性：整车订单号，用于对账
     */
    @Excel(name = "extend_attr")
    private String extendAttr;

    /**
     * 是否删除 0：否 1：是
     */
    @Excel(name = "deleted")
    private Integer deleted;

    /**
     * 创建时间
     */
    @Excel(name = "created_at")
    private String createdAt;

    /**
     * 创建人
     */
    @Excel(name = "created_by")
    private String createdBy;

    /**
     * 更新时间
     */
    @Excel(name = "updated_at")
    private String updatedAt;

    /**
     * 修改人
     */
    @Excel(name = "updated_by")
    private String updatedBy;

}
