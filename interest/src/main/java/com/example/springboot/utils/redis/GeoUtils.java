package com.example.springboot.utils.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class GeoUtils {
    private  static  Logger logger=Logger.getLogger("根据经纬度计算:");
    public static final String KEY="B3pm8MYFWyuYAPoNRdtaWwcOG8qWsGdV";//AK  根据百度地图申请 认证 给的ak  该AK 不对任何ip 限制  0.0.0.0/00

    /**
     * 计算两经纬度点之间的距离（单位：米）
     * @param lng1  经度
     * @param lat1  纬度
     * @param lng2
     * @param lat2
     * @
     * @return
     */
    public static double getDistance(double lng1,double lat1,double lng2,double lat2){
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;
        logger.info("两个位置之间的距离是"+s+"米");
        return s;
    }

    /**
     *
     * @param Lat 经度
     * @param Long 纬度
     * @des   根据经纬度得出来 具体的位置
     */
    public static Map<String, String> LatlongPositon(String Lat,String Long){
        //请求百度地图的ip地址
        String url="http://api.map.baidu.com/geocoder/v2/?ak="+KEY+"&location="+Lat+","+Long+"&output=json&pois=0";
        logger.info(url);
        InputStream is = null;
        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder jsonText = new StringBuilder();
            String len="";
            while ((len = rd.readLine()) != null) {
                jsonText.append(len);
            }
            JSONObject json= JSON.parseObject(jsonText.toString());
//            JSON//Object json = JSONPObject.
            logger.info("定位得出的参数=="+json);
            String city="";//市
            String country="";//国家
            String province="";//省
            String district="";//区
            //当status =0  代表成功解析到地址
            if (json!=null&&"0".equals(json.getString("status"))) {
                JSONObject result=json.getJSONObject("result");//解析result
                if (result!=null) {
                    JSONObject addressComponent=result.getJSONObject("addressComponent");
                    city=addressComponent.getString("city");
                    country=addressComponent.getString("country");
                    province=addressComponent.getString("province");
                    district=addressComponent.getString("district");

                }
            }
            Map<String, String> resMap=new HashMap<String, String>();
            resMap.put("city", city);
            resMap.put("country", country);
            resMap.put("province", province);
            resMap.put("district", district);
            logger.info(resMap);
            return resMap;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        } finally {
            try {
                if(is!=null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
//        //得出来两个地方的距离  根据经纬度
//        logger.info(getDistance(121.446014,31.215937,121.446028464238,31.2158502442799  ));
        //根据经纬度得出来 具体的位置
        System.out.println(LatlongPositon("30.2084", "120.21201"));
    }

}