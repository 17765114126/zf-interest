package com.example.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@Configuration
public class RedisConfigMQ {

    private final String ACTIVITY_SEND_TOPIC = "RedisMQSendTopic";

//    private final String POLITICAL_BIRTHDAY_TOPIC = "politicalBirthdayTopic";

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter,
                                            MessageListenerAdapter listenerAdapter2) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //Topic
        container.addMessageListener(listenerAdapter, new PatternTopic(ACTIVITY_SEND_TOPIC));
//        container.addMessageListener(listenerAdapter2, new PatternTopic(POLITICAL_BIRTHDAY_TOPIC));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        //receiveMessage:接收方法的名称
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

//    @Bean
//    MessageListenerAdapter listenerAdapter2(Receiver receiver) {
//        //receiveMessage:接收方法的名称
//        return new MessageListenerAdapter(receiver, "receiveMessage2");
//    }

    @Bean
    Receiver receiver(CountDownLatch latch) {
        return new Receiver(latch);
    }

    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }
}