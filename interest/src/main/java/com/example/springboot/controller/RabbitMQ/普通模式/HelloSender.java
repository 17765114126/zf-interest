package com.example.springboot.controller.RabbitMQ.普通模式;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName HelloSender
 * @Author zhaofu
 * @Date 2019/10/22
 * @Version V1.0
 * @Description: 发送者
 **/
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
    * @Date: 2019/10/22
    * @Author: zhaofu
    * @Description: 普通模式
    **/
    public void send() {
        //24小时制
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = "hello " + date;
        System.out.println("Sender : " + context);
        //简单对列的情况下routingKey即为Q名
        this.rabbitTemplate.convertAndSend("q_hello", context);
    }
    /**
     * @Date: 2019/10/22
     * @Author: zhaofu
     * @Description: Work模式
     **/
    public void send(int i) {
        //24小时制
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = "hello " + i + " " + date;
        System.out.println("Sender : " + context);
        //简单对列的情况下routingKey即为Q名
        this.rabbitTemplate.convertAndSend("q_hello", context);
    }

}
