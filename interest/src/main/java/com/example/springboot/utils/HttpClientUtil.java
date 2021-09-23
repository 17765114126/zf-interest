package com.example.springboot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Author yinwei
 * @create 2021/7/27
 * @Version 1.0
 */
@Slf4j
public class HttpClientUtil {


    @Value("${order.chargingPile.detail.url.appId}")
    private static String appId="111";
    @Value("${order.chargingPile.detail.url.appscret}")
    private static String appscret="222";

    /**
     * 通过post方式调用http接口
     * @param url     url路径
     * @param jsonParam    json格式的参数
     * @param reSend     重发次数
     * @return
     * @throws Exception
     */
    public static String sendPostByJson(String url, String jsonParam,int reSend) {
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime=System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime= 0L;
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            // 创建连接
            httpClient = HttpClientFactory.getInstance().getHttpClient();
            // 设置请求头和报文
            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);
            Header header=new BasicHeader("Accept-Encoding",null);
            httpPost.setHeader(header);
            // 设置报文和通讯格式
            StringEntity stringEntity = new StringEntity(jsonParam, HttpConstant.UTF8_ENCODE);
            //stringEntity.setContentEncoding(HttpConstant.UTF8_ENCODE);
            stringEntity.setContentType(HttpConstant.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            //httpPost.s

            //签名
//            GetChargingPileByOrderNoParam param = JSON.parseObject(jsonParam, GetChargingPileByOrderNoParam.class);
            String linkString =jsonParam;
            String requestParamsStr = URLEncoder.encode(linkString, "utf-8")
                    .replace("+", "%20").replace("*", "%2A").replace("$7E", "~");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(requestParamsStr.getBytes(StandardCharsets.UTF_8));
            byte[] md5Bytes = messageDigest.digest();
            String contentMD5 = Base64Utils.encodeToString(md5Bytes);
            long timestamp = System.currentTimeMillis();
            Random random = new Random(1000);
            int nonce = random.nextInt()+1000;
            String stringSign = requestParamsStr+contentMD5+timestamp+nonce;
            Mac mac = Mac.getInstance("HMACSHA1");
            mac.init(new SecretKeySpec(appscret.getBytes(StandardCharsets.UTF_8),"HMACSHA1"));
            byte[] signData = mac.doFinal(stringSign.getBytes(StandardCharsets.UTF_8));
            String sign = Base64Utils.encodeToString(signData);
            httpPost.addHeader("x-app-id",appId);
            //httpPost.addHeader("appSecret",APPSACRET);
            httpPost.addHeader("x-sign",sign);
            httpPost.addHeader("x-nonce",String.valueOf(nonce));
            httpPost.addHeader("x-timestamp",String.valueOf(timestamp));
            log.info("请求{}接口的参数为{}",url,jsonParam);

            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            httpEntity= httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            log.error("请求{}接口出现异常",url,e);
            if (reSend > 0) {
                log.info("请求{}出现异常:{}，进行重发。进行第{}次重发",url,e.getMessage(),(HttpConstant.REQ_TIMES-reSend +1));
                result = sendPostByJson(url, jsonParam, reSend - 1);
                if (result != null && !"".equals(result)) {
                    return result;
                }
            }
        }finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                log.error("http请求释放资源异常",e);
            }
        }
        //请求接口的响应时间
        endTime=System.currentTimeMillis();
        log.info("请求{}接口的响应报文内容为{},本次请求API接口的响应时间为:{}毫秒",url,result,(endTime-startTime));
        return result;

    }


    /**
     * 通过post方式调用http接口
     * @param url     url路径
     * @param map    json格式的参数
     * @param reSend     重发次数
     * @return
     * @throws Exception
     */
    public static String sendPostByForm(String url, Map<String,String> map,int reSend) {
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime=System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime= 0L;
        HttpEntity httpEntity = null;
        UrlEncodedFormEntity entity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            // 创建连接
            httpClient = HttpClientFactory.getInstance().getHttpClient();
            // 设置请求头和报文
            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            entity = new UrlEncodedFormEntity(list,HttpConstant.UTF8_ENCODE);
            httpPost.setEntity(entity);
            log.info("请求{}接口的参数为{}",url,map);
            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            httpEntity= httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            log.error("请求{}接口出现异常",url,e);
            if (reSend > 0) {
                log.info("请求{}出现异常:{}，进行重发。进行第{}次重发",url,e.getMessage(),(HttpConstant.REQ_TIMES-reSend +1));
                result = sendPostByForm(url, map, reSend - 1);
                if (result != null && !"".equals(result)) {
                    return result;
                }
            }
        }finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                log.error("http请求释放资源异常",e);
            }
        }
        //请求接口的响应时间
        endTime=System.currentTimeMillis();
        log.info("请求{}接口的响应报文内容为{},本次请求API接口的响应时间为:{}毫秒",url,result,(endTime-startTime));
        return result;

    }
    /**
     * 通过post方式调用http接口
     * @param url     url路径
     * @param xmlParam    json格式的参数
     * @param reSend     重发次数
     * @return
     * @throws Exception
     */
    public static Object sendPostByXml(String url, String xmlParam,int reSend) {
        //声明返回结果
        Object result = "";
        //开始请求API接口时间
        long startTime = System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime = 0L;
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            // 创建连接
            httpClient = HttpClientFactory.getInstance().getHttpClient();
            // 设置请求头和报文
            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);
            StringEntity stringEntity = new StringEntity(xmlParam, HttpConstant.UTF8_ENCODE);
            stringEntity.setContentEncoding(HttpConstant.UTF8_ENCODE);
            stringEntity.setContentType(HttpConstant.TEXT_XML);
            httpPost.setEntity(stringEntity);
            log.info("请求{}接口的参数为{}", url, xmlParam);
            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity,HttpConstant.UTF8_ENCODE);
        } catch (Exception e) {
            log.error("请求{}接口出现异常", url, e);
            if (reSend > 0) {
                log.info("请求{}出现异常:{}，进行重发。进行第{}次重发", url, e.getMessage(), (HttpConstant.REQ_TIMES - reSend + 1));
                result = sendPostByJson(url, xmlParam, reSend - 1);
                if (result != null && !"".equals(result)) {
                    return result;
                }
            }
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                log.error("http请求释放资源异常", e);
            }
            //请求接口的响应时间
            endTime = System.currentTimeMillis();
            log.info("请求{}接口的响应报文内容为{},本次请求API接口的响应时间为:{}毫秒", url, result, (endTime - startTime));
            return result;
        }

    }

    public static String base64AndMD5(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes can not be null");
        } else {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.reset();
                md.update(bytes);
                byte[] md5Result = md.digest();
                String base64Result = Base64.encodeBase64String(md5Result);
                return base64Result.length() > 24 ? base64Result.substring(0, 24) : base64Result;
            } catch (NoSuchAlgorithmException var4) {
                throw new IllegalArgumentException("unknown algorithm MD5");
            }
        }
    }
}