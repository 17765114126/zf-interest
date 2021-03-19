package com.example.springboot.controller.RabbitMQ.主题模式;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

/**
 * @ClassName Receiver1
 * @Author zhaofu
 * @Date 2019/10/22
 * @Version V1.0
 * @Description: 创建2个消费者
 **/

@Component
//@RabbitListener(queues = "q_topic_messages")//使用请打开，否则会一直连接
public class Receiver2 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver2 : " + hello);
    }
}