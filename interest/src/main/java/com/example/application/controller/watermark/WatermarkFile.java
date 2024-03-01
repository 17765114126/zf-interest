package com.example.application.controller.watermark;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName D
 * @Author zhaofu
 * @Date 2021/3/11
 * @Version V1.0
 **/
public class WatermarkFile {

    //java 通过url下载图片保存到本地
    public static void main(String[] args) throws IOException {

        URL url1 = new URL("https://oss.dorago.cn/cms/2020/8/c&400*400&291596698056973.jpg");
        URLConnection uc = url1.openConnection();
        InputStream inputStream = uc.getInputStream();

        FileOutputStream out = new FileOutputStream("F:/qie_rotateqq.jpg");
        int j = 0;
        while ((j = inputStream.read()) != -1) {
            out.write(j);
        }
        inputStream.close();

    }

    /**
     * 上传水印图片
     */
    public String uploadWatermarkFile()
            throws IOException {

        String srcUrl = "https://oss.dorago.cn/cms/2021/3/c111615366642612.jpg";
        //阿里云添加水印方法
//        srcUrl += "?x-oss-process=image/resize,w_700,h_700/watermark,type_d3F5LXplbmhlaQ,size_30,text_5py15ouJ5bmz5Y-w6JCl5Lia5omn54Wn5L-h5oGv5YWs56S65LiT55So,color_FFFFFF,size_15,shadow_10,rotate_299,fill_1,t_50,g_se,x_10,y_10";
        URL url1 = new URL(srcUrl.replaceAll("\r|\n", ""));
        URLConnection uc = url1.openConnection();
        InputStream input = uc.getInputStream();

        /**读取后缀名，再次获取文件流  (文件流不可以使用第二次)*/
        InputStream input1 = url1.openConnection().getInputStream();
        byte[] b = new byte[3];
        input1.read(b, 0, b.length);
        String xxx = bytesToHexString(b);
        xxx = xxx.toUpperCase();//头文件
        String ooo = checkType(xxx);//后缀名

        //上传
//        String key = ALiYunUtil.createCmsKey(ooo);

//        ObjectMetadata objectMeta = new ObjectMetadata();
//        objectMeta.setContentLength(uc.getContentLength());
        // 可以在metadata中标记文件类型
//        objectMeta.setContentType("image/jpeg");

//        try {
//            PutObjectResult result=client.putObject(BUCKET_NAME, key, input, objectMeta);
//            input.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //返回存储路径
//        String msg = ALiYunUtil.IMAGE_URL + key;
//        return msg;
        return "";

    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     常用文件的文件头如下：(以前六位为准)
     JPEG (jpg)，文件头：FFD8FF
     PNG (png)，文件头：89504E47
     GIF (gif)，文件头：47494638
     TIFF (tif)，文件头：49492A00
     Windows Bitmap (bmp)，文件头：424D
     CAD (dwg)，文件头：41433130
     Adobe Photoshop (psd)，文件头：38425053
     Rich Text Format (rtf)，文件头：7B5C727466
     XML (xml)，文件头：3C3F786D6C
     HTML (html)，文件头：68746D6C3E
     Email [thorough only] (eml)，文件头：44656C69766572792D646174653A
     Outlook Express (dbx)，文件头：CFAD12FEC5FD746F
     Outlook (pst)，文件头：2142444E
     MS Word/Excel (xls.or.doc)，文件头：D0CF11E0
     MS Access (mdb)，文件头：5374616E64617264204A
     WordPerfect (wpd)，文件头：FF575043
     Postscript (eps.or.ps)，文件头：252150532D41646F6265
     Adobe Acrobat (pdf)，文件头：255044462D312E
     Quicken (qdf)，文件头：AC9EBD8F
     Windows Password (pwl)，文件头：E3828596
     ZIP Archive (zip)，文件头：504B0304
     RAR Archive (rar)，文件头：52617221
     Wave (wav)，文件头：57415645
     AVI (avi)，文件头：41564920
     Real Audio (ram)，文件头：2E7261FD
     Real Media (rm)，文件头：2E524D46
     MPEG (mpg)，文件头：000001BA
     MPEG (mpg)，文件头：000001B3
     Quicktime (mov)，文件头：6D6F6F76
     Windows Media (asf)，文件头：3026B2758E66CF11
     MIDI (mid)，文件头：4D546864
     */
    public static String checkType(String xxxx) {

        switch (xxxx) {
            case "FFD8FF": return "jpg";
            case "89504E": return "png";
            case "474946": return "jif";

            default: return "0000";
        }
    }

}
