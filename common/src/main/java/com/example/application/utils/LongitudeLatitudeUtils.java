package com.example.application.utils;

import redis.clients.jedis.GeoCoordinate;

import java.math.BigDecimal;
import java.util.*;

/**
 * Description:
 * Date： 2019/7/11.
 *
 * @author haoWen
 */
public class LongitudeLatitudeUtils {
    /**
     * 地球半径,单位 m
     */
    private static final double EARTH_RADIUS = 6378137.00D;

    /**
     *
     * 弧度
     *
     */
    private static final double RADIAN = Math.PI / 180.00D;

    /**
     *
     * 度
     *
     */
    private static final double DEGREE = 180/ Math.PI;

    /**
     *
     * 1/2
     *
     */
    private static final double HALF = 0.5D;

    /**
     * 根据经纬度，计算两点间的距离
     * <p>
     * Math.pow(x,y)      这个函数是求x的y次方
     * Math.toRadians     将一个角度测量的角度转换成以弧度表示的近似角度
     * Math.sin           正弦函数
     * Math.cos           余弦函数
     * Math.sqrt          求平方根函数
     * Math.asin          反正弦函数
     *
     * @param currentLongitudeLatitude 当前经纬度 eg 30.540167218713037,119.95319366455078
     * @param longitudeLatitude        对比的经纬度 eg 30.536027405159054,119.95697021484375
     * @return 返回距离 单位：米
     */
    public static double getDistance(String currentLongitudeLatitude, String longitudeLatitude) {

        //第一个点的经度
        Double longitude1 = Double.valueOf(currentLongitudeLatitude.substring(currentLongitudeLatitude.indexOf(",") + 1));

        //第一个点的纬度
        Double latitude1 = Double.valueOf(currentLongitudeLatitude.substring(0, currentLongitudeLatitude.indexOf(",")));

        //第二个点的经度
        Double longitude2 = Double.valueOf(longitudeLatitude.substring(longitudeLatitude.indexOf(",") + 1));

        //第二个点的纬度
        Double latitude2 = Double.valueOf(longitudeLatitude.substring(0, longitudeLatitude.indexOf(",")));
        return CalculationDistance(longitude1,latitude1,longitude2,latitude2);
    }



    /**
     *
     * 根据经纬度，计算两点间的距离
     * <p>
     * Math.pow(x,y)      这个函数是求x的y次方
     * Math.toRadians     将一个角度测量的角度转换成以弧度表示的近似角度
     * Math.sin           正弦函数
     * Math.cos           余弦函数
     * Math.sqrt          求平方根函数
     * Math.asin          反正弦函数
     *
     * @param longitude1
     * @param latitude1
     * @param longitude2
     * @param latitude2
     * @return
     */
    public static double CalculationDistance(Double longitude1, Double latitude1, Double longitude2, Double latitude2) {
        double x, y, a, b, distance;

        latitude1 *= RADIAN;
        latitude2 *= RADIAN;
        x = latitude1 - latitude2;
        y = longitude1 - longitude2;
        y *= RADIAN;
        a = Math.sin(x * HALF);
        b = Math.sin(y * HALF);
        distance = EARTH_RADIUS * Math.asin(Math.sqrt(a * a + Math.cos(latitude1) * Math.cos(latitude2) * b * b)) / HALF;
        return new BigDecimal(distance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    private static GeoCoordinate getGeoCoordinate(List<GeoCoordinate> geoCoordinateList) {
        int total = geoCoordinateList.size();
        double X = 0, Y = 0, Z = 0;
        for (GeoCoordinate g : geoCoordinateList) {
            double lat, lon, x, y, z;
            //将角度转换为弧度
            lat = g.getLatitude() * RADIAN;
            lon = g.getLongitude() * RADIAN;
            //转换为笛卡尔坐标
            x = Math.cos(lat) * Math.cos(lon);
            y = Math.cos(lat) * Math.sin(lon);
            z = Math.sin(lat);
            X += x;
            Y += y;
            Z += z;
        }
        //加权平均
        X = X / total;
        Y = Y / total;
        Z = Z / total;
        double Lon = Math.atan2(Y, X);
        double Hyp = Math.sqrt(X * X + Y * Y);
        double Lat = Math.atan2(Z, Hyp);
        return new GeoCoordinate(Lat * DEGREE, Lon * DEGREE);
    }


    /**
     * longitude 经度 与本初子午线的角度
     *
     * latitude 纬度 与赤道的角度
     *
     *
     *  根据输入的地点坐标计算中心点
     * @param coordinateList
     * @return
     */
    public static GeoCoordinate getCenterPointA(List<GeoCoordinate> coordinateList) {
        return getGeoCoordinate(coordinateList);
    }

}
