package com.example.springboot.controller.RabbitMQ.Work模式;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloReceiver2
 * @Author zhaofu
 * @Date 2019/10/22
 * @Version V1.0
 * 注册第二个Receiver（这样就两个了）:
 **/

@Component
//@RabbitListener(queues = "q_hello")//使用请打开，否则会一直连接
public class HelloReceiver2 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver2  : " + hello);
    }

}

