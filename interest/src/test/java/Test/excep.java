package Test;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.springboot.utils.ID.SnowIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * @ClassName excep
 * @Author zhaofu
 * @Date 2021/8/13
 * @Version V1.0
 **/
@Slf4j
public class excep {


    public static void main(String[] args) throws IOException {
//        查询导出sql
//        SELECT bo.id as orderId,bo.biz_order_no,po.id as poId,bo.biz_type, po.account_id,po.biz_order_pay_id,po.out_pay_id ,po.pay_amt,po.sub_order_type,po.pay_way, pod.id as podId,pod.out_pay_id podOpId FROM biz_order bo
//        LEFT JOIN zeekrlife_mp_pay.pay_order po on bo.id = po.biz_order_id
//        LEFT JOIN zeekrlife_mp_pay.pay_order_details pod on po.id = pod.pay_order_id
//        where bo.`biz_order_no` in('B0220411NKQYEI','B02205013XHZNB','B02204308YZLDJ','B0220429H5PT2S','B0220419WWJKJL','B0220428LNXBII','B0220429IHC8AP','B0220429B4JSI8','B0220428A4RHXQ','B0220419DIXHBA','B0220418DKFB1R')
        List<ImportExcelDTO> prodDTO = getmportExcel("D:\\excel\\退定金.xlsx");
        for (ImportExcelDTO importExcelDTO : prodDTO) {
            long payOrderRefundId = SnowIdUtils.uniqueLong();
            String time = "2022-09-28 18:24:09";
            String sql =
                    "update zeekrlife_mp_order.biz_order \n" +
                            "set \n" +
                            "paid_amt = 0.00,\n" +
                            "advance_payment = 0.00,\n" +
                            "client_order_status = 5 ,\n" +
                            "unsubscribe_status = 7,\n" +
                            "refund_create_time = '" + time + "',\n" +
                            "refund_time = '" + time + "'\n" +
                            "where id = " + importExcelDTO.getId() + " AND biz_order_no = '" + importExcelDTO.getBizOrderNo() + "';\n" +

                            "\n" +
                            "INSERT INTO zeekrlife_mp_pay.pay_order_refund\n" +
                            "(`id`, `account_id`, \n" +
                            "`pay_order_id`, `biz_order_id`, `biz_order_pay_id`, \n" +
                            "`out_refund_id`, \n" +
                            "`org_out_pay_id`, \n" +
                            "`sub_order_type`, `org_pay_amt`, `refund_amt`,\n" +
                            " `refund_status`, `pay_way`, \n" +
                            " `refund_create_time`, `refund_time`, `refund_reason`, `other_reason`, `refund_expire_time`, `fail_msg`, `extend_attr`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`, `biz_type`, `refund_integral`) \n" +
                            "VALUES \n" +
                            "(" + payOrderRefundId + ", " + importExcelDTO.getAccountId() + ",\n" +
                            " " + importExcelDTO.getPoId() + ", " + importExcelDTO.getId() + ", " + importExcelDTO.getBizOrderPayId() + ", \n" +
                            " '', \n" +
                            " '" + importExcelDTO.getOutPayId() + "', \n" +
                            " " + importExcelDTO.getSubOrderType() + ", " + importExcelDTO.getPayAmt() + ", " + importExcelDTO.getPayAmt() + ", \n" +
                            " 3, " + importExcelDTO.getPayWay() + ", \n" +
                            " '" + time + "', '" + time + "', '', '', NULL, '', '" + importExcelDTO.getBizOrderNo() + "', 0, now(), '', now(), '', '" + importExcelDTO.getBizType() + "', NULL);\n" +

                            "\n" +
                            "INSERT INTO zeekrlife_mp_pay.pay_order_refund_details \n" +
                            "(`id`, `pay_order_refund_id`, `pay_order_details_id`, \n" +
                            "`out_refund_id`,\n" +
                            " `org_out_pay_id`, \n" +
                            "`sub_order_type`, `org_pay_amt`, `refund_amt`, `refund_status`, `pay_way`, " +
                            "`refund_create_time`, `refund_time`, `refund_reason`, `other_reason`, `refund_expire_time`, `fail_msg`, `extend_attr`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`, `biz_type`, `refund_integral`) \n" +
                            "VALUES \n" +
                            "(" + SnowIdUtils.uniqueLong() + ", " + payOrderRefundId + ", " + importExcelDTO.getPodId() + ", \n" +
                            "'', \n" +
                            "'" + importExcelDTO.getPodOpId() + "', " +
                            "" + importExcelDTO.getSubOrderType() + ", " + importExcelDTO.getPayAmt() + ", " + importExcelDTO.getPayAmt() + ", 3, " + importExcelDTO.getPayWay() + ", " +
                            "'" + time + "', '" + time + "', '', '', NULL, '', '" + importExcelDTO.getBizOrderNo() + "', 0,  now(), '',  now(), '', '" + importExcelDTO.getBizType() + "', NULL);";
            System.out.println(sql);
        }
    }


    /**
     * 获取文件流
     */
    public static List<ImportExcelDTO> getmportExcel(String url) throws IOException {

        //获取文件流
        MultipartFile multipartFile = getMultipartFile(url);

        ImportParams params = new ImportParams();
        InputStream ins = null;
        List<ImportExcelDTO> list = null;
        try {
            ins = multipartFile.getInputStream();
            list = ExcelImportUtil.importExcel(ins, ImportExcelDTO.class, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ins.close();
            } catch (Exception e) {
                log.error("Io异常");
            }
        }
        return list;
    }

    /**
     * 获取文件流
     */
    public static MultipartFile getMultipartFile(String url) throws IOException {
        File file = new File(url);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        return multipartFile;
    }

}




