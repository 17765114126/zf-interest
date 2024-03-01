package com.example.application.controller.Redis;

import redis.clients.jedis.JedisPubSub;

public class MQClientTest extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println(channel + ":" + message);
    }

    public static void main(String[] args) {
        MQClient client = new MQClient("127.0.0.1", 6379, new MQClientTest());
        client.setValue("abc", "java setted");
        System.out.println(client.getValue("abc"));
        System.out.println(client.clientNotify("nodeSubscriber", "message from java", "{\"debug\":0}", MQClient.MSG_REALTIME));
        client.subscribe("testInitiativePerception");
    }

}
