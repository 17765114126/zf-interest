package com.example.application.utils.weather;

import com.alibaba.fastjson.JSON;
import com.example.application.utils.weather.dto.WeatherInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.zip.GZIPInputStream;

/**
 * @Author: CaoTing
 * @Description:  通过get请求向中华万年历网站http://wthrcdn.etouch.cn/weather_mini获取某个城市的天气状况数据，数据格式是Json
 * @Date: 2019/7/4
 */
public class WeatherUtils {
    /**
     * 通过城市名称获取该城市的天气信息
     *
     * @param cityName
     * @return
     */

    public  static String getWeatherData(String cityName) {
        StringBuilder sb=new StringBuilder();;
        try {
            cityName = URLEncoder.encode(cityName, "UTF-8");
            String weatherRrl = "http://wthrcdn.etouch.cn/weather_mini?city="+cityName;
//            String weatherRrl = "https://free-api.heweather.net/s6/weather/now?location="+cityName+"&key=db86a5196f304e52a4369818c5182e60";

            URL url = new URL(weatherRrl);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            GZIPInputStream gzin = new GZIPInputStream(is);

            // 设置读取流的编码格式，自定义编码
            InputStreamReader isr = new InputStreamReader(gzin, "utf-8");
            BufferedReader reader = new BufferedReader(isr);
            String line = null;
            while((line=reader.readLine())!=null) {
                sb.append(line).append(" ");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }


    /**
     * 将JSON格式数据进行解析 ，返回一个对象
     * @param weatherInfoByJson
     * @return
     */
    public static WeatherInfo getWeather(String info){
        WeatherInfo weatherInfo = JSON.parseObject(info, WeatherInfo.class);
        return weatherInfo;
    }
    public static void main(String[] args){
        String info = WeatherUtils.getWeatherData("成都");
        WeatherInfo weather = WeatherUtils.getWeather(info);
        System.out.println(JSON.toJSONString(weather));
    }
}
