package Test;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName excep
 * @Author zhaofu
 * @Date 2021/8/13
 * @Version V1.0
 **/
@Slf4j
public class excep2 {


    public static void main(String[] args) throws IOException {
//
//        String q = "";
//        String[] split = q.split(",");
////        查询导出sql
//        List<ImportExcelDTO> prodDTO = getmportExcel("D:\\opt\\老人信息导入2.xlsx");
//        for (ImportExcelDTO importExcelDTO : prodDTO) {
//
//            if (ObjectUtil.isNull(importExcelDTO.getCart()) || ObjectUtil.isNull(importExcelDTO.getAddress())) {
//                continue;
//            }
////            String sql = "UPDATE future_community.wl_student_or_elder " +
////                    "SET student_card= '"+importExcelDTO.getCart().trim()+"', id_card = '"+importExcelDTO.getCartNum().trim()+"' " +
////                    "WHERE type = 2 AND name = '"+importExcelDTO.getName().trim()+"';";
//
////            for (String s : split) {
////                if (importExcelDTO.getCartNum().trim().equals(s.trim())) {
////                    String schoolId = "";
////                    String schoolName = "";
////                    if (ObjectUtil.isNull(importExcelDTO.getWorkplace())) {
////                        schoolId = "1645682750290096128";
////                        schoolName = "秋山小学";
////                    } else {
////                        schoolId = "1645682827133939712";
////                        schoolName = "秋山中学";
////                    }
//
//                    String communityId = "";
//                    String communityName = "";
//                    if (importExcelDTO.getAddress().contains("兆丰欣苑") || importExcelDTO.getAddress().contains("兆丰新苑") || importExcelDTO.getAddress().contains("兆丰兴苑")) {
//                        communityId = "1645680911398170624";
//                        communityName = "兆丰欣苑";
//                    } else if (importExcelDTO.getAddress().contains("秋山明苑") || importExcelDTO.getAddress().contains("秋山名苑")) {
//                        communityId = "1645707056902533120";
//                        communityName = "秋山明苑";
//                    } else if (importExcelDTO.getAddress().contains("秋北嘉苑") || importExcelDTO.getAddress().contains("秋北家苑") || importExcelDTO.getAddress().contains("秋北佳苑")) {
//                        communityId = "1645707206689517568";
//                        communityName = "秋北嘉苑";
//                    }
//                    String sql = "INSERT INTO `future_community`.`wl_student_or_elder_copy` " +
//                            "(`name`, `student_card`, `id_card`, `community_id`, `community_name`, `classes_school`, `type`) " +
//                            "VALUES " +
//                            "( '" + importExcelDTO.getName().trim() + "', '" + importExcelDTO.getCart().trim() + "', '" + importExcelDTO.getCartNum().trim() + "', '" + communityId + "', '" + communityName + "',  '" + importExcelDTO.getWorkplace() + "',  '2');";
//                    System.out.println(sql);
//                }
//            }

//            String sql = "UPDATE future_community.wl_student_or_elder " +
//                    "SET student_card= '"+importExcelDTO.getCart().trim()+"', id_card = '"+importExcelDTO.getCartNum().trim()+"' " +
//                    "WHERE type = 1 AND name = '"+importExcelDTO.getCartName().trim()+"';";



//            String sql = "INSERT INTO future_community.wl_student_or_elder_copy (`name`, `student_card`, `id_card`) " +
//                    "VALUES ('"+importExcelDTO.getCartName().trim()+"', '"+importExcelDTO.getCart().trim()+"', '"+importExcelDTO.getCartNum().trim()+"');";

//            long payOrderRefundId = SnowIdUtils.uniqueLong();
//            String time = "2022-09-28 18:24:09";
//            String sql =
//                    "update zeekrlife_mp_order.biz_order " +
//                            "set " +
//                            "paid_amt = 0.00," +
//                            "advance_payment = 0.00," +
//                            "client_order_status = 5 ," +
//                            "unsubscribe_status = 7," +
//                            "refund_create_time = '" + time + "'," +
//                            "refund_time = '" + time + "'" +
//                            "where id = " + importExcelDTO.getId() + " AND biz_order_no = '" + importExcelDTO.getBizOrderNo() + "';" +
//
//                            "" +
//                            "INSERT INTO zeekrlife_mp_pay.pay_order_refund" +
//                            "(`id`, `account_id`, " +
//                            "`pay_order_id`, `biz_order_id`, `biz_order_pay_id`, " +
//                            "`out_refund_id`, " +
//                            "`org_out_pay_id`, " +
//                            "`sub_order_type`, `org_pay_amt`, `refund_amt`," +
//                            " `refund_status`, `pay_way`, " +
//                            " `refund_create_time`, `refund_time`, `refund_reason`, `other_reason`, `refund_expire_time`, `fail_msg`, `extend_attr`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`, `biz_type`, `refund_integral`) " +
//                            "VALUES " +
//                            "(" + payOrderRefundId + ", " + importExcelDTO.getAccountId() + "," +
//                            " " + importExcelDTO.getPoId() + ", " + importExcelDTO.getId() + ", " + importExcelDTO.getBizOrderPayId() + ", " +
//                            " '', " +
//                            " '" + importExcelDTO.getOutPayId() + "', " +
//                            " " + importExcelDTO.getSubOrderType() + ", " + importExcelDTO.getPayAmt() + ", " + importExcelDTO.getPayAmt() + ", " +
//                            " 3, " + importExcelDTO.getPayWay() + ", " +
//                            " '" + time + "', '" + time + "', '', '', NULL, '', '" + importExcelDTO.getBizOrderNo() + "', 0, now(), '', now(), '', '" + importExcelDTO.getBizType() + "', NULL);" +
//
//                            "" +
//                            "INSERT INTO zeekrlife_mp_pay.pay_order_refund_details " +
//                            "(`id`, `pay_order_refund_id`, `pay_order_details_id`, " +
//                            "`out_refund_id`," +
//                            " `org_out_pay_id`, " +
//                            "`sub_order_type`, `org_pay_amt`, `refund_amt`, `refund_status`, `pay_way`, " +
//                            "`refund_create_time`, `refund_time`, `refund_reason`, `other_reason`, `refund_expire_time`, `fail_msg`, `extend_attr`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`, `biz_type`, `refund_integral`) " +
//                            "VALUES " +
//                            "(" + SnowIdUtils.uniqueLong() + ", " + payOrderRefundId + ", " + importExcelDTO.getPodId() + ", " +
//                            "'', " +
//                            "'" + importExcelDTO.getPodOpId() + "', " +
//                            "" + importExcelDTO.getSubOrderType() + ", " + importExcelDTO.getPayAmt() + ", " + importExcelDTO.getPayAmt() + ", 3, " + importExcelDTO.getPayWay() + ", " +
//                            "'" + time + "', '" + time + "', '', '', NULL, '', '" + importExcelDTO.getBizOrderNo() + "', 0,  now(), '',  now(), '', '" + importExcelDTO.getBizType() + "', NULL);";

//        }
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




