package com.example.springboot.config.自定义注解;

import com.example.springboot.model.MyLogClass;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * @ClassName MyLogService
 * @Author zhaofu
 * @Date 2019/10/18
 * @Version V1.0
 **/
@Service
public class MyLogService {


    public MyLogClass getTblId(String value, String params, Class LogMethod) {//定义的判断参数，参数所对应的对象。

        MyLogClass myLogClass = new MyLogClass();
        //如果是删除-直接插入删除的id；否则查询修改或者添加数据的id
        if (value != null) {
            if (value.contains("-delete")) {
                String tblId = params.replace("\"", "");//"2,3"
                myLogClass.setTblId(tblId);
                return myLogClass;
            }
            Gson gson = new Gson();
            if (LogMethod != null) {
                Object object = gson.fromJson(params, LogMethod);

                String tblId = fieldOut(object, "id");
                String productId = fieldOut(object, "productId");
                String qdNumber = fieldOut(object, "qdNumber");

                myLogClass.setTblId(tblId);
                myLogClass.setProductId(productId);
                myLogClass.setQdNumber(qdNumber);
            }
        }
        return myLogClass;
    }

    /**
     * @Description: 反射获取指定字段
     **/
    public String fieldOut(Object object, String val) {
        String tblId = null;
        try {
            Field field = object.getClass().getDeclaredField(val);
            //对private的属性的访问
            field.setAccessible(true);
            try {
                tblId = field.get(object).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return tblId;
    }

}
