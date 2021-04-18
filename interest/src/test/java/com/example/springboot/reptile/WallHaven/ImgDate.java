package com.example.springboot.reptile.WallHaven;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springboot.mapper.WallHavenMapper;
import com.example.springboot.model.entity.WallHaven;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ImgDate {
    @Resource
    WallHavenMapper wallHavenMapper;

    int start = 24;//每页多少条
    int total = 0;//记录数
    int end = 130;//总共130条页

    @Test
    public void Test(){

//        downImages("E:\\wallHaven","https://w.wallhaven.cc/full/pk/wallhaven-pk92ve.png");

        /**
         * 准备抓取的目标地址，https://wallhaven.cc
         */
        String url = "https://wallhaven.cc/toplist?page=";
        for (int i = 11; i < end; i++) {
            try {
                getImages(url+i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("正在爬取中---正在抓取第:" + i + "页数据");
            if (i == end){
                System.out.println("已经爬取到底了");
            }
        }

    }


    public void getImages(String url) throws Exception {
        //链接到目标地址
        Connection connect = Jsoup.connect(url);
        //设置useragent,设置超时时间，并以get请求方式请求服务器
        Document document = connect.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").timeout(6000).ignoreContentType(true).get();
        Thread.sleep(1000);
        //获取指定标签的数据
        Element elementById = document.getElementById("thumbs");
        //输出文本数据
//        System.out.println(elementById.text());
        //输出html数据
//        System.out.println(elementById.html());

        //获取所有图片链接
        Elements imgtag = elementById.getElementsByTag("a");
        List<String> imgurlList = new ArrayList<>();
        for (int i = 0; i < imgtag.size(); i++) {
            if (StringUtils.isNotEmpty(imgtag.get(i).attr("href"))&&imgtag.get(i).attr("href").startsWith("http")) {
                insertImages(imgtag.get(i).attr("href"));
            }
        }
    }
    public void insertImages(String img) throws Exception {
        //链接到目标地址
        Connection connect = Jsoup.connect(img);
        //设置useragent,设置超时时间，并以get请求方式请求服务器
        Document document = connect.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").timeout(6000).ignoreContentType(true).get();
        Thread.sleep(1000);
        //获取指定标签的数据
        Element elementById = document.getElementById("wallpaper");
        //获取所有图片链接
        Elements imgtag = elementById.getElementsByTag("img");
        for (int i = 0; i < imgtag.size(); i++) {
            if (StringUtils.isNotEmpty(imgtag.get(i).attr("src"))&&imgtag.get(i).attr("src").startsWith("http")) {
                WallHaven selectOne = wallHavenMapper.selectOne(new LambdaQueryWrapper<WallHaven>()
                        .eq(WallHaven::getImgUrl,imgtag.get(i).attr("src")));
                if (selectOne != null){
                    continue;
                }
                WallHaven wallHaven = new WallHaven();
                wallHaven.setImgUrl(imgtag.get(i).attr("src"));
                wallHavenMapper.insert(wallHaven);
                System.out.println("正在爬取中---共抓取:" + total++ + "条数据");
            }
        }
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
            // 获得连接
            URLConnection connection = url.openConnection();
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
