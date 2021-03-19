package com.example.springboot.controller.RabbitMQ.订阅模式;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

/**
 * @ClassName ReceiverA
 * @Author zhaofu
 * @Date 2019/10/22
 * @Version V1.0
 * @Description: 创建3个消费者
 **/

@Component
//@RabbitListener(queues = "q_fanout_C")//使用请打开，否则会一直连接
public class ReceiverC {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("CReceiver  : " + hello + "/n");
    }

}