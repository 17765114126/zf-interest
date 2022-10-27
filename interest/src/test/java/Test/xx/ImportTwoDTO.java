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
public class ImportTwoDTO {

    @Excel(name = "总订单号", width = 200)
    private String bizOrderNo;

    @Excel(name = "VIN码", width = 200)
    private String vin;

    @Excel(name = "活体实名激活状态", width = 200)
    private String status;


}
