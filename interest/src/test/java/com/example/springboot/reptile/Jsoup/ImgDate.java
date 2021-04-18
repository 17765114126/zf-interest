package com.example.springboot.reptile.Jsoup;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ImgDate {
    /**
     * 准备抓取的目标地址，%E4%BA%92%E8%81%94%E7%BD%91 为utf-8格式的 互联网
     */
    private static String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=互联网";

    public static void main(String[] args) throws Exception {
        //链接到目标地址
        Connection connect = Jsoup.connect(url);
        //设置useragent,设置超时时间，并以get请求方式请求服务器
        Document document = connect.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").timeout(6000).ignoreContentType(true).get();
        Thread.sleep(1000);
        //获取指定标签的数据
        Element elementById = document.getElementById("content_left");
        //输出文本数据
        System.out.println(elementById.text());
        //输出html数据
        System.out.println(elementById.html());

        //获取所有图片链接
        Elements imgtag = document.getElementsByTag("img");
        List<String> imgurlList = new ArrayList<String>();
        for (int i = 0; i < imgtag.size(); i++) {
            if (StringUtils.isNotEmpty(imgtag.get(i).attr("src"))&&imgtag.get(i).attr("src").startsWith("http")) {
                System.out.println(imgtag.get(i).attr("src"));
            }
        }
    }

    @Test
    public void Test(){
        downImages("E:\\wallHaven","https://w.wallhaven.cc/full/pk/wallhaven-pk92ve.png");
    }

    /**
     * 下载图片到指定目录
     *
     * @param filePath 文件路径
     * @param imgUrl   图片URL
     */
    public static void downImages(String filePath, String imgUrl) {
        // 若指定文件夹没有，则先创建
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 截取图片文件名
        String fileName = imgUrl.substring(imgUrl.lastIndexOf('/') + 1, imgUrl.length());

        try {
            // 文件名里面可能有中文或者空格，所以这里要进行处理。但空格又会被URLEncoder转义为加号
            String urlTail = URLEncoder.encode(fileName, "UTF-8");
            // 因此要将加号转化为UTF-8格式的%20
            imgUrl = imgUrl.substring(0, imgUrl.lastIndexOf('/') + 1) + urlTail.replaceAll("\\+", "\\%20");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 写出的路径
        File file = new File(filePath + File.separator + fileName);

        try {
            // 获取图片URL
            URL url = new URL(imgUrl);
            url.openConnection().setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            // 获得连接
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.addRequestProperty("Mozilla/4.0"," Mozilla / 4.76");

            String contentType = connection.getContentType();

            System.out.println("contentType:" + contentType);

            // 设置10秒的相应时间
            connection.setConnectTimeout(10 * 1000);
            // 获得输入流
            InputStream in = connection.getInputStream();
            // 获得输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            // 构建缓冲区
            byte[] buf = new byte[1024];
            int size;
            // 写入到文件
            while (-1 != (size = in.read(buf))) {
                out.write(buf, 0, size);
            }
            out.close();
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
