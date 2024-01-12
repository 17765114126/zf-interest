package Test;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.utils.ID.SnowIdUtils;
import com.example.springboot.utils.string.StringUtil;
import com.example.springboot.utils.string.StringUtils;
import com.example.springboot.utils.string.StringUtilss;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName excep
 * @Author zhaofu
 * @Date 2021/8/13
 * @Version V1.0
 **/
@Slf4j
public class excep {


    public static void main(String[] args) throws IOException {

//        String q = "8899880911B7";
//        String[] split = q.split(",");
//        查询导出sql
        List<ImportExcelDTO> prodDTO = getmportExcel("D:\\opt\\幼儿.XLSX");
        LocalDate of = LocalDate.of(2023, 3, 21);
        for (ImportExcelDTO importExcelDTO : prodDTO) {
            long payOrderRefundId = SnowIdUtils.uniqueLong();
            Random random = new SecureRandom();
            Integer x = ThreadLocalRandom.current().nextInt(3, 8 + 1);

            Integer child_sex = random.nextInt(2);



            String str = "INSERT INTO `future_community`.`wl_childcare_bespeak`" +
                        "(`institution_id`, `bespeak_name`, `bespeak_mobile`, `child_name`, `child_sex`, `child_age`, `status`, `reserve_time_id`, `wl_time_period_id`, `create_user_uuid`, `create_time`, `last_update_user_uuid`, `last_update_time`, `del_flag`) \n" +
                        "VALUES \n" +
                        "('1663384218053922816', '"+importExcelDTO.getName()+"', '"+importExcelDTO.getPhone()+"', '"+importExcelDTO.getCName()+"', '"+child_sex+"', '"+x+"', '1', '"+payOrderRefundId+"', '1663384218116837376', 'a08d223d4ebe491c971e50325b8fc1a7', '2023-04-03 11:23:43', 'a08d223d4ebe491c971e50325b8fc1a7', '2023-04-03 11:23:43', '0');";
                System.out.println(str);
                String str1 = "INSERT INTO `future_community`.`wl_venues_reserve_time` \n" +
                        "(`id`, `institution_id`, `AM_open`, `PM_open`, `type`, `open_data`, `create_user_uuid`, `create_time`, `last_update_user_uuid`, `last_update_time`, `del_flag`) \n" +
                        "VALUES \n" +
                        "('"+payOrderRefundId+"', '1645310176615964672', '1', '1', '1', '"+of+"', 'qwrwaedfzsd', '2023-04-10 14:18:09', 'qwrwaedfzsd', '2023-04-10 14:18:09', '0');";
                System.out.println(str1);
            of = of.plusDays(1);


//            if (ObjectUtil.isNull(importExcelDTO.getCart())) {
//                continue;
//            }
//            String sql = "UPDATE future_community.wl_student_or_elder " +
//                    "SET student_card= '"+importExcelDTO.getCart().trim()+"', id_card = '"+importExcelDTO.getCartNum().trim()+"' " +
//                    "WHERE type = 1 AND name = '"+importExcelDTO.getName().trim()+"';";

//            for (String s : split) {
//                if (importExcelDTO.getCartNum().trim().equals(s.trim())) {
//                    String schoolId = "";
//                    String schoolName = "";
//                    if (ObjectUtil.isNull(importExcelDTO.getWorkplace())) {
//                        schoolId = "1645682750290096128";
//                        schoolName = "秋山小学";
//                    } else {
//                        schoolId = "1645682827133939712";
//                        schoolName = "秋山中学";
//                    }

//                    String communityId = "";
//                    String communityName = "";
//                    if (importExcelDTO.getAddress().contains("兆丰欣苑") || importExcelDTO.getAddress().contains("兆丰新苑")
//                            || importExcelDTO.getAddress().contains("兆丰兴苑")) {
//                        communityId = "1645680911398170624";
//                        communityName = "兆丰欣苑";
//                    } else if (importExcelDTO.getAddress().contains("秋山明苑") || importExcelDTO.getAddress().contains("秋山名苑")
//                            || importExcelDTO.getAddress().contains("秋山朋苑")) {
//                        communityId = "1645707056902533120";
//                        communityName = "秋山明苑";
//                    } else if (importExcelDTO.getAddress().contains("秋北嘉苑") || importExcelDTO.getAddress().contains("秋北家苑")
//                            || importExcelDTO.getAddress().contains("秋北佳苑")|| importExcelDTO.getAddress().contains("秋北嘉园")) {
//                        communityId = "1645707206689517568";
//                        communityName = "秋北嘉苑";
//                    }
//                    String sql = "INSERT INTO `future_community`.`wl_student_or_elder_quan` " +
//                            "(`name`, `student_card`, `id_card`, `school_id`, `school_name`, `community_id`, `community_name`, `classes_school`) " +
//                            "VALUES " +
//                            "( '" + importExcelDTO.getName().trim() + "', '" + importExcelDTO.getCart().trim() + "', '" + importExcelDTO.getCartNum().trim() + "', '" + schoolId + "', '" + schoolName + "', '" + communityId + "', '" + communityName + "',  '" + importExcelDTO.getWorkplace() + "');";
//                    System.out.println(sql);
//                }
//            }
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




