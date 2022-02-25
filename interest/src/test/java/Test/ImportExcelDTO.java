package Test;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @Description 导入
 * @Author zf
 * @Date 2019/10/29 16:17
 * @Version 1.0
 */
@Data
public class ImportExcelDTO {
    @Excel(name = "id", width = 12)
    private String id;

//    @Excel(name = "安装完成时间", width = 25, format = "yyyy-MM-dd HH:mm:ss")
//    private Date succedTime;

    @Excel(name = "account_id", width = 200)
    private String accountId;

    @Excel(name = "phone_no", width = 200)
    private String phoneNo;

    @Excel(name = "sales_phone", width = 200)
    private String salesPhone;

    @Excel(name = "deliver_adviser_phone", width = 200)
    private String deliverAdviserPhone;

    @Excel(name = "user_mobile_phone", width = 200)
    private String userMobilePhone;
}
