package com.example.springboot.controller.RabbitMQ.普通模式;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloReceiver
 * @Author zhaofu
 * @Date 2019/10/22
 * @Version V1.0
 * @Description: 接收者
 **/
@Component
//@RabbitListener(queues = "q_hello")//使用请打开，否则会一直连接
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }

}