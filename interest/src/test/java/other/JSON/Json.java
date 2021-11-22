package other.JSON;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.utils.number.MathUtil;
import com.example.springboot.model.form.Result;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

/**
 * @ClassName Json
 * @Author zhaofu
 * @Date 2019/11/20
 * @Version V1.0
 **/
public class Json {
    /**
     * @Date: 2019/11/19
     * @Author: zhaofu
     * @Description: Json转换
     **/
    @ResponseBody
    @RequestMapping("/customInfo.html")
    public String customInfo(@ModelAttribute(value = "orderId") String data) {
        data = "[{\"android\":\"4\",\"id\":1,\"iphoneFive\":\"2\",\"iphoneFour\":\"1\",\"iphonePlus\":\"5\",\"iphoneSix\":\"3\",\"iphonex\":\"6\",\"times\":1,\"title\":\"测试\",\"type\":1}]";
        //"[{"android":"4","id":1,"iphoneFive":"2","iphoneFour":"1","iphonePlus":"5","iphoneSix":"3","iphonex":"6","times":1,"title":"测试","type":1}]"
        JSONArray jsonArray = JSON.parseArray(data);
        Map<String, Object> map = null;
        ArrayList<String> list = new ArrayList<String>();
        if (jsonArray != null) {
            int len = jsonArray.size();
            for (int i = 0; i < len; i++) {
                JSONObject json = (JSONObject) JSONObject.parse(jsonArray.get(i).toString());
                map = json;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    list.add(entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        return JSON.toJSONString(Result.with(map));
    }

    public static void main(String[] args) {
        Double xpPrice = Double.parseDouble("5.455555");
        xpPrice = MathUtil.toInteger(xpPrice);
        System.out.println(xpPrice);

        DecimalFormat decimalFormat = new DecimalFormat("#");
//        Double xpPrice = Double.parseDouble("99")*0.95;
        BigDecimal bd = new BigDecimal("12.1");
        BigDecimal bigDecimal = bd.setScale(0, BigDecimal.ROUND_DOWN);
//        decimalFormat.format(xpPrice);
    }
}
