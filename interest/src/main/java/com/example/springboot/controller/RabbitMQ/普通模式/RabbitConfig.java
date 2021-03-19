package com.example.springboot.controller.RabbitMQ.普通模式;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitConfig
 * @Author zhaofu
 * @Date 2019/10/22
 * @Version V1.0
 * @Description: 配置队列
 **/
@Configuration
public class RabbitConfig {
    @Bean
    public Queue queue() {
        return new Queue("q_hello");
    }
}
