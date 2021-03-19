package study.JSON;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test
 * @Author zhaofu
 * @Date 2020/7/31
 * @Version V1.0
 *
 *
 * fastjson 三个主要的类：
 *
 * JSONObject  代表 json 对象
 *
 * JSONObject实现了Map接口, 猜想 JSONObject底层操作是由Map实现的。
 *
 * JSONObject对应json对象，通过各种形式的get()方法可以获取json对象中的数据，也可利用诸如size()，isEmpty()等方法获取"键：值"对的个数和判断是否为空。其本质是通过实现Map接口并调用接口中的方法完成的。
 *
 * JSONArray   代表 json 对象数组
 *
 * 内部是有List接口中的方法来完成操作的。
 *
 * JSON代表 JSONObject和JSONArray的转化
 *
 * JSON类源码分析与使用
 *
 * 仔细观察这些方法，主要是实现json对象，json对象数组，javabean对象，json字符串之间的相互转化。
 *
 **/
public class Test {
    public static void main(String[] args) {
        //创建一个对象
        Person user1 = new Person("1号", 3, "男");
        Person user2 = new Person("2号", 3, "男");
        Person user3 = new Person("3号", 3, "男");
        Person user4 = new Person("4号", 3, "男");
        List<Person> list = new ArrayList();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        System.out.println(" *******Java对象 转 JSON字符串*******");
        String str1 = JSON.toJSONString(list);
        System.out.println(" JSON.toJSONString(list)==>" + str1);
        String str2 = JSON.toJSONString(user1);
        System.out.println(" JSON.toJSONString(user1)==>" + str2);

        System.out.println(" ****** JSON字符串 转 Java对象*******");
        Person jp_user1 = JSON.parseObject(str2, Person.class);
        System.out.println("JSON.parseObject(str2,User.class)==>" + jp_user1);

        System.out.println(" ****** Java对象 转 JSON对象 ******");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
        System.out.println("(JSONObject) JSON.toJSON(user2)==>" + jsonObject1.getString("name"));

        System.out.println(" ****** JSON对象 转 Java对象 ******");
        Person to_java_user = JSON.toJavaObject(jsonObject1, Person.class);
        System.out.println(" JSON.toJavaObject(jsonObject1, User.class)==>" + to_java_user);
    }
}
