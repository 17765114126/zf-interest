package com.example.springboot.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
//@RefreshScope
@Component
public class FeiShuRobotUtils {

//    @Value("${ali.feiShuRobotTest.url:}")
    private String dfeiShuRobotTestUrl ="https://open.feishu.cn/open-apis/bot/v2/hook/da8091eb-454e-4a0a-ac90-cef41acd024a";

//    @Value("${ali.feiShuRobotProd.url:}")
    private String feiShuRobotProdUrl = "https://open.feishu.cn/open-apis/bot/v2/hook/da8091eb-454e-4a0a-ac90-cef41acd024a";

    @Autowired
    private RestTemplate restTemplate;

//    @Value("${spring.profiles.active:dev}")
    private String active = "test";

//    @Value("${robot.msg.length:1000}")
    private Integer robotMsgLength = 1000;

    @Async
    public void send(String ddrobotUrl, Map<String, Object> items){
        try {
            restTemplate.postForEntity(ddrobotUrl,items,String.class);


            ResponseEntity<String> forEntity = restTemplate.getForEntity(ddrobotUrl, String.class);
        } catch (Exception e) {
//            log.error("send dingding error:{}", ExUtils.getEDetail(e));
        }
    }

    public void sendMsg(String text) {
//        String ddrobotUrl = active.equalsIgnoreCase(CoreConstants.ENV_TEST) || active.equalsIgnoreCase(CoreConstants.ENV_PRE) ? dfeiShuRobotTestUrl : feiShuRobotProdUrl;
        Map<String, Object> items = new HashMap<>();

        items.put("msg_type", "text");
        Map<String, String> textContent = new HashMap<>();
        if(text.length() > robotMsgLength){
            textContent.put("text", text.substring(0,robotMsgLength));
        }else{
            textContent.put("text", text);
        }
        items.put("content", textContent);
        Map<String, Object> atItems = new HashMap<>();
        items.put("at", atItems);
        send(dfeiShuRobotTestUrl,items);
    }
}
