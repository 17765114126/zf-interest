package com.example.springboot.utils.aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.example.springboot.utils.aliyun.dto.SmsClient;

/**
 * @description 阿里大鱼短信接口
 * @create 2016年7月7日, 下午1:41:28
 */
public class AliSmsUtil {
    static final String signName = "";//应用签名
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "";
    //产品域名,开发者无需替换
    static final String domain = "";
    static final String accessKeyId = "";
    static final String accessKeySecret = "";
//    //短信模板私人定制客户
//    public static final String SMS_CLIENT = "SMS_200720408";

    public static SendSmsResponse sendSms(String mobile, String code, String modelCode) {
        SendSmsResponse sendSmsResponse = null;
        try {
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(mobile);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(modelCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(code);

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("smsSend");

            //hint 此处可能会抛出异常，注意catch
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendSmsResponse;
    }

    public static void main(String[] args) throws Exception {
        //SMS_200710484
        //${name}您好，私人定制派单号${orderNumber}，客户${userName}，${phone}，地址：${address}，请及时与客户确认上门服务时间。

        //SMS_200720408
        //尊敬的客户，您的私人定制需求已受理，现已为您安排着装顾问${name}上门服务，电话${phone}，感谢您的惠顾。
//		String code = "{\"code\":\"" + 123456 + "\"}";
        SmsClient smsClient = new SmsClient("张三先生", "17765114126");
        String modelCode = "SMS_200720408";
        String string = JSON.toJSONString(smsClient);
        SendSmsResponse srm = AliSmsUtil.sendSms("17765114126", string, modelCode);
        System.out.println(srm.getMessage());
    }
}
