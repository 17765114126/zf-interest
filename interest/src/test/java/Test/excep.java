package Test;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.springboot.utils.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName excep
 * @Author zhaofu
 * @Date 2021/8/13
 * @Version V1.0
 **/
@Slf4j
public class excep {

    /**
     * @Description 导入
     * @Author zf
     * @Date 2019/10/29 16:17
     * @Version 1.0
     */
    public static void main(String[] args) throws IOException {

        List<ImportExcelDTO> prodDTO = getmportExcel("D:\\excel\\用户相关-biz_owner_info-线上.xlsx");
        List<ImportExcelDTO> testDTO = getmportExcel("D:\\excel\\用户相关-biz_owner_info-test.xlsx");

        System.out.println("_____----");
        Set<String> objects = new HashSet<>();
        for (ImportExcelDTO prod : prodDTO) {
            for (ImportExcelDTO test : testDTO) {

                if (Objects.nonNull(prod.getAccountId()) && Objects.nonNull(prod.getAccountId())){
                    if (prod.getAccountId().equals(test.getAccountId())){
//                        System.out.println(test.getId());
                        objects.add(test.getId());
                    }
                }

                if (Objects.nonNull(prod.getPhoneNo()) && Objects.nonNull(test.getPhoneNo())){
                    if (prod.getPhoneNo().equals(test.getPhoneNo())){
//                        System.out.println(test.getId());
                        objects.add(test.getId());
                    }
                }
//
//                if (Objects.nonNull(prod.getSalesPhone()) && Objects.nonNull(test.getSalesPhone())){
//                    if (prod.getSalesPhone().equals(test.getSalesPhone())){
//                        System.out.println(test.getId());
//
//                        objects.add(test.getId());
//                    }
//                }
//                if (Objects.nonNull(prod.getUserMobilePhone()) && Objects.nonNull(test.getUserMobilePhone())){
//                    if (prod.getUserMobilePhone().equals(test.getUserMobilePhone())){
//                        System.out.println(test.getId());
//                        objects.add(test.getId());
//                    }
//                }
//
//                if (Objects.nonNull(prod.getDeliverAdviserPhone()) &&Objects.nonNull(test.getDeliverAdviserPhone())){
//                    if (prod.getDeliverAdviserPhone().equals(test.getDeliverAdviserPhone())){
//                        System.out.println(test.getId());
//                        objects.add(test.getId());
//                    }
//                }
//                if ((Objects.nonNull(prod.getAccountId()) && Objects.nonNull(test.getAccountId()) && prod.getAccountId().equals(test.getAccountId())) ||
//                        (Objects.nonNull(prod.getPhoneNo()) && Objects.nonNull(test.getPhoneNo()) && prod.getPhoneNo().equals(test.getPhoneNo()))||
//                        (Objects.nonNull(prod.getSalesPhone()) && Objects.nonNull(test.getSalesPhone()) && prod.getSalesPhone().equals(test.getSalesPhone()))||
//                        (Objects.nonNull(prod.getUserMobilePhone()) && Objects.nonNull(test.getUserMobilePhone()) && prod.getUserMobilePhone().equals(test.getUserMobilePhone()))||
//                        (Objects.nonNull(prod.getDeliverAdviserPhone()) &&Objects.nonNull(test.getDeliverAdviserPhone()) && prod.getDeliverAdviserPhone().equals(test.getDeliverAdviserPhone()))){
//                    objects.add(test.getId());
//                    System.out.println(test.getId()+",");
//                }

            }
        }

        System.out.println("----------------------------------");


        for (String object : objects) {
            System.out.println(object+",");
        }
        String s = "INSERT INTO zeekrlife_mp_order.biz_order (`id`, `biz_order_no`, `account_id`, `phone_no`, `cert_no`, `wx_open_id`, `biz_type`, `order_type`, `order_amt`, `actual_price`, `paid_amt`, `advance_payment`, `un_pay_amt`, `order_status`, `client_order_status`, `order_source`, `sales_id`, `sales`, `sales_phone`, `is_change`, `unsubscribe_status`, `refund_create_time`, `refund_time`, `refund_reason`, `refund_detail_reason`, `pay_time`, `close_time`, `close_reason`, `extend_attr`, `deposit_protocol_url`, `deposit_time`, `user_confirm_time`, `pno_code`, `deliver_adviser`, `deliver_adviser_phone`, `deliver_center`, `deliver_area`, `deliver_area_code`, `collection_way`, `pre_collection_time`, `deleted`, `created_at`, `created_by`, `updated_at`, `updated_by`, `pay_deposit_time`, `handover_time`, `contract_id`, `contract_state`, `contract_url`, `user_certificates_type`, `user_certificates_code`, `user_mobile_phone`, `user_name`, `car_vin`, `battery_code`, `support_transfer`, `car_orders_type`, `agreement_best_sign_url`, `insurance`, `proceeds_account`, `account_bank`, `dedicated_account`, `buy_store_name`, `buy_store_code`, `buy_store_type`, `buy_store_id`, `sales_store_code`, `sales_store_name`, `sales_area_code`, `sales_area_name`, `transit_update_time`, `pickup_update_time`, `contract_type`, `order_status_deliver_time`, `text`, `deposit_sales`, `deliver_sales`, `cancel_start`) VALUES (1496671496975872000, 'B02202244K9RV6', 1496044309443256320, '13562250447', '', '', 'B01', 2, 315000.00, 282000.00, 0.01, 0.01, 309999.99, 10, 10, 3, 637805968863924224, '韩增文(经理)', '18438091019', 0, 0, NULL, NULL, '', '', '2021-12-07 00:00:00', NULL, '', '', 'https://zeekrlife-resource-web-test.lkhaowu.com/pages/zeekr/car/purchaseAgreement/v1.2.html', NULL, '2022-02-24 11:01:06', '', '', NULL, 'Z010', NULL, NULL, 1, NULL, 0, '2021-06-10 10:00:00', '13562250447', '2022-02-24 17:07:26', 'system', '2021-12-07 00:00:00', '2022-02-24 16:28:45', NULL, NULL, '', NULL, '', '', '', '', '', 1, 1, NULL, 0, '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, '', '', '', 0);\n";

    }

    /**
     * 获取文件流
     * */
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
     * */
    public static MultipartFile getMultipartFile(String url) throws IOException {
        File file = new File(url);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        return multipartFile;
    }

}




