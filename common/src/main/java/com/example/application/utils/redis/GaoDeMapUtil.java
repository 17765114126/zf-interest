package com.example.application.utils.redis;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.Map;

public class GaoDeMapUtil {
    //高德地图key值
    private static final String mapKey = "e7240bd19224922daf263ef32fd31d07";

    public static void main(String[] args) {
//        JSONArray objects = searchMapNearby("吉利大厦", "杭州", 1, 2);
//        System.out.println(objects);

        JSONArray objects1 = searchMapInputtips("吉利大厦", "");
        System.out.println(objects1);
    }

    /**
     *根据经纬度获取省市区
     * @param lat
     * @return
     */
    public static String getCoordinate(Double lng, Double lat) throws IOException {
        String url = "http://restapi.amap.com/v3/geocode/regeo";
        Map<String,Object> parameters = Maps.newHashMap();
        parameters.put("key",mapKey);
        parameters.put("location",lng+","+lat);
        parameters.put("poitype","");
        parameters.put("radius","");
        parameters.put("extensions","base");
        parameters.put("batch","true");
        String resultData = HttpUtil.get(url, parameters);
        if (StringUtils.isNotBlank(resultData)) {
            JSONObject jsonObject = (JSONObject) JSON.parseObject(resultData.toString()).getJSONArray("regeocodes").get(0);
            JSONObject addressComponent = jsonObject.getJSONObject("addressComponent");
            return addressComponent.getStr("province").replace("省","")+""+addressComponent.getStr("city");
        }else{
            return "";
        }
    }


    /**
     * 根据ip获取地址
     * @param ip
     * @return
     */
    public static String getAddressByIp(String ip){
//        log.info("getAddressByIp ip={}",ip);
        try {
            String url = "https://restapi.amap.com/v3/ip";
            Map<String,Object> parameters = Maps.newHashMap();
            //ip=114.247.50.2&output=xml&key=<用户的key>
            parameters.put("ip",ip);
            parameters.put("key",mapKey);
            String jsonResult = HttpUtil.get(url, parameters);
            if (null!=jsonResult) {
//                log.info("getAddressByIp result={}",jsonResult);
                com.alibaba.fastjson.JSONObject resultObj = JSON.parseObject(jsonResult);
                String returnStr = resultObj.getString("province")+","+resultObj.getString("city");
                if(returnStr.contains("[],[]")){
                    return "地球村";
                }
                return returnStr;
            }
        } catch (Exception e) {
//            log.error("getAddressByIp error={}",e);
        }
        return "地球村";
    }


    /**
     * 查找附近的数据
     * @param keywords
     * @param city
     * @param page
     * @param pagesize
     * @return
     */
    public static JSONArray searchMapNearby(String keywords,String city,int page,int pagesize){
//        log.info("searchMapNearby keywords={}",keywords);
        try {
            String url = "https://restapi.amap.com/v3/place/text";
            Map<String,Object> parameters = Maps.newHashMap();
            //restapi.amap.com/v3/place/text?key=您的key&keywords=电影院&types=&city=深圳&children=1&offset=20&page=1&extensions=all
            parameters.put("key",mapKey);
            parameters.put("keywords",keywords);
            parameters.put("types","");
            parameters.put("city",city);
            parameters.put("children",1);
            parameters.put("offset",pagesize);
            parameters.put("page",page);
            parameters.put("extensions","all");
            String jsonResult = HttpUtil.get(url, parameters);
            if (StringUtils.isNotBlank(jsonResult)) {
//                log.info("searchMapNearby result={}",jsonResult);
                com.alibaba.fastjson.JSONObject resultJson = JSON.parseObject(jsonResult);
                com.alibaba.fastjson.JSONArray pois = resultJson.getJSONArray("pois");
                return pois;
            }
        } catch (Exception e) {
//            log.error("searchMapNearby error={}",e);
            //异常返回空数组
            return new JSONArray();
        }
        return null;
    }


    /**
     * 地址输入提示
     * @param keywords
     * @param city
     * @param page
     * @param pagesize
     * @return
     */
    public static JSONArray searchMapInputtips(String keywords,String city){
//        log.info("searchMapNearby keywords={}",keywords);
        try {
            String url = "https://restapi.amap.com/v3/assistant/inputtips";
            Map<String,Object> parameters = Maps.newHashMap();
            //restapi.amap.com/v3/place/text?key=您的key&keywords=电影院&types=&city=深圳&children=1&offset=20&page=1&extensions=all
            parameters.put("key",mapKey);
            parameters.put("keywords",keywords);
            parameters.put("type","");
            parameters.put("city",city);
            parameters.put("location","");
            parameters.put("citylimit","");
            parameters.put("sig","");
            parameters.put("output","JSON");
            parameters.put("datatype","all");
            String jsonResult = HttpUtil.get(url, parameters);
            if (StringUtils.isNotBlank(jsonResult)) {
//                log.info("searchMapNearby result={}",jsonResult);
                com.alibaba.fastjson.JSONObject resultJson = JSON.parseObject(jsonResult);
                com.alibaba.fastjson.JSONArray pois = resultJson.getJSONArray("tips");
                return pois;
            }
        } catch (Exception e) {
//            log.error("searchMapNearby error={}",e);
            //异常返回空数组
            return new JSONArray();
        }
        return null;
    }


//    public static void main(String[] args) {
//        // lat 31.2990170 纬度
//        // log 121.3466440 经度
//        String add = GaoDeMapUtil.getCity("116.407394", "39.904211");
//
//        System.out.println(add);
//    }

//    /**
//     * 阿里云api 根据经纬度获取地址
//     *
//     * @param log
//     * @param lat
//     * @return
//     */
//    public static String getAdd(String log, String lat) {
//        StringBuffer s = new StringBuffer();
//        s.append("key=").append(key).append("&location=").append(log).append(",").append(lat);
//        String res = sendPost("http://restapi.amap.com/v3/geocode/regeo", s.toString());
//        logger.info(res);
//        JSONObject jsonObject = JSONObject.fromObject(res);
//        JSONObject jsonObject1 = JSONObject.fromObject(jsonObject.get("regeocode"));
//        logger.info("jsonObject1-->" + jsonObject1);
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        resultMap.put("addressComponent", jsonObject1.get("addressComponent"));
//        System.out.println("addressComponent--->" + resultMap.get("addressComponent"));
//        JSONObject jsonObject2 = JSONObject.fromObject(jsonObject1.get("addressComponent"));
//        System.out.println("province--->" + jsonObject2.get("province"));
//        String add = jsonObject1.get("formatted_address").toString();
//        return add;
//    }
//
//    /**
//     * 阿里云api 根据经纬度获取所在城市
//     *
//     * @param log
//     * @param lat
//     * @return
//     */
//    public static String getCity(String log, String lat) {
//        // log 大 lat 小
//        // 参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
//        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l=" + lat + "," + log + "&type=010";
//        String res = "";
//        try {
//            URL url = new URL(urlString);
//            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            java.io.BufferedReader in = new java.io.BufferedReader(
//                    new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
//            String line;
//            while ((line = in.readLine()) != null) {
//                res += line + "\n";
//            }
//            in.close();
//            JSONObject jsonObject = JSONObject.fromObject(res);
//            JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("addrList"));
//            JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
//            String allAdd = j_2.getString("admName");
//            String arr[] = allAdd.split(",");
//            res = arr[1];
//        } catch (Exception e) {
//            logger.info("error in wapaction,and e is " + e.getMessage());
//        }
//        logger.info(res);
//        return res;
//    }
//
//    /**
//     * 高德api 根据地址获取经纬度
//     *
//     * @param name
//     * @return
//     */
//    public static String getLatAndLogByName(String name) {
//        StringBuffer s = new StringBuffer();
//        s.append("key=" + key + "&address=" + name);
//        String res = sendPost("http://restapi.amap.com/v3/geocode/geo", s.toString());
////        logger.info(res);
//        JSONObject jsonObject = JSONObject.fromObject(res);
//        JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("geocodes"));
//        JSONObject location = (JSONObject) jsonArray.get(0);
//        String add = location.get("location").toString();
//        return add;
//    }
//
//    /**
//     * 高德api 根据地址获取经纬度
//     *
//     * @param name
//     * @return
//     */
//    public static String getAddByAMAP(String log, String lat) {
//        StringBuffer s = new StringBuffer();
//        s.append("key=").append(key).append("&location=").append(log).append(",").append(lat);
//        String res = sendPost("http://restapi.amap.com/v3/geocode/regeo", s.toString());
////        logger.info(res);
//        JSONObject jsonObject = JSONObject.fromObject(res);
//        JSONObject jsonObject1 = JSONObject.fromObject(jsonObject.get("regeocode"));
//        String add = jsonObject1.get("formatted_address").toString();
//        return add;
//    }
//
//    /**
//     * 高德api 坐标转换---转换至高德经纬度
//     *
//     * @param name
//     * @return
//     */
//    public static String convertLocations(String log, String lat, String type) {
//        StringBuffer s = new StringBuffer();
//        s.append("key=").append(key).append("&locations=").append(log).append(",").append(lat).append("&coordsys=");
//        if (type == null) {
//            s.append("gps");
//        } else {
//            s.append(type);
//        }
//        String res = sendPost("http://restapi.amap.com/v3/assistant/coordinate/convert", s.toString());
////        logger.info(res);
//        JSONObject jsonObject = JSONObject.fromObject(res);
//        String add = jsonObject.get("locations").toString();
//        return add;
//    }
//
//    public static String getAddByName(String name) {
//        // log 大 lat 小
//        // 参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
//        String urlString = "http://gc.ditu.aliyun.com/geocoding?a=" + name;
//        String res = "";
//        try {
//            URL url = new URL(urlString);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//            String line;
//            while ((line = in.readLine()) != null) {
//                res += line + "\n";
//            }
//            in.close();
//            JSONObject jsonObject = JSONObject.fromObject(res);
//            String lon = jsonObject.getString("lon");
//            String lat = jsonObject.getString("lat");
//            System.err.println(jsonObject);
//            res = getNearbyAdd(lon, lat);
//        } catch (Exception e) {
//            logger.info("error in wapaction,and e is " + e.getMessage());
//            e.printStackTrace();
//        }
//        return res;
//    }
//
//    public static String getNearbyAdd(String log, String lat) {
//
//        String add = sendGet("http://ditu.amap.com/service/regeo",
//                "longitude=" + log + "&latitude=" + lat + "&type=010");
//
////        logger.info(add);
//
//        return add;
//    }
//
//    /**
//     * 高德api 关键字模糊查询
//     *
//     * @param keyWord
//     * @param city
//     * @return
//     */
//    public static String getKeywordsAddByLbs(String keyWord, String city) {
//        StringBuffer s = new StringBuffer();
//        s.append("key=" + key + "&keywords=");
//        if (keyWord.contains(" ")) {
//            String[] str = keyWord.split(" ");
//            for (int i = 0; i < str.length; i++) {
//                if (i == 0) {
//                    s.append(str[i]);
//                } else {
//                    s.append("+" + str[i]);
//                }
//            }
//        } else {
//            s.append(keyWord);
//        }
//        s.append("&city=" + city);
//        s.append("offset=10&page=1");
//        String around = sendPost("http://restapi.amap.com/v3/place/text", s.toString());
////        logger.info(around);
//        return around;
//    }
//
//    /**
//     * 高德api 经纬度/关键字 附近地标建筑及地点查询
//     *
//     * @param log
//     * @param lat
//     * @param keyWord
//     * @return
//     */
//    public static String getAroundAddByLbs(String log, String lat, String keyWord) {
//        String around = sendPost("http://restapi.amap.com/v3/place/around", "key=" + key + "&location=" + log + ","
//                + lat + "&keywords=" + keyWord + "&radius=2000&offset=10&page=1");
////        logger.info(around);
//        return around;
//    }
//
//    public static String sendGet(String url, String param) {
//        String result = "";
//        BufferedReader in = null;
//        try {
//            String urlNameString = url + "?" + param;
//            URL realUrl = new URL(urlNameString);
//            // 打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection();
//            // 设置通用的请求属性
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 建立实际的连接
//            connection.connect();
//            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
////                logger.info(key + "--->" + map.get(key));
//            }
//            // 定义 BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
////            logger.info("发送GET请求出现异常！" + e);
//            e.printStackTrace();
//        }
//        // 使用finally块来关闭输入流
//        finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 向指定 URL 发送POST方法的请求
//     *
//     * @param url   发送请求的 URL
//     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//     * @return 所代表远程资源的响应结果
//     */
//    public static String sendPost(String url, String param) {
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//            // 发送请求参数
//            out.print(param);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            logger.info("发送 POST 请求出现异常！" + e);
//            e.printStackTrace();
//        }
//        // 使用finally块来关闭输出流、输入流
//        finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    /**
//     * GET请求数据
//     *
//     * @param get_url url地址
//     * @param content key=value形式
//     * @return 返回结果
//     * @throws Exception
//     */
//    public static String sendGetData(String get_url, String content) throws Exception {
//        String result = "";
//        URL getUrl = null;
//        BufferedReader reader = null;
//        String lines = "";
//        HttpURLConnection connection = null;
//        try {
//            if (content != null && !content.equals(""))
//                get_url = get_url + "?" + content;
//            // get_url = get_url + "?" + URLEncoder.encode(content, "utf-8");
//            getUrl = new URL(get_url);
//            connection = (HttpURLConnection) getUrl.openConnection();
//            connection.connect();
//            // 取得输入流，并使用Reader读取
//            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 设置编码
//            while ((lines = reader.readLine()) != null) {
//                result = result + lines;
//            }
//            return result;
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            if (reader != null) {
//            }
//        }
//    }
}