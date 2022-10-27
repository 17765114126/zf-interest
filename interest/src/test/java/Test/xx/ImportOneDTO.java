package Test.xx;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @Description 导入
 * @Author zf
 * @Date 2019/10/29 16:17
 * @Version 1.0
 */
@Data
public class ImportOneDTO {


    @Excel(name = "bizOrderId", width = 200)
    private String bizOrderId;

    @Excel(name = "rnrId", width = 200)
    private String rnrId;


    @Excel(name = "biz_order_no", width = 200)
    private String bizOrderNo;

    @Excel(name = "certification_status", width = 200)
    private String certificationStatus;

    @Excel(name = "car_vin", width = 200)
    private String carVin;

    @Excel(name = "deleted", width = 200)
    private String deleted;
}
