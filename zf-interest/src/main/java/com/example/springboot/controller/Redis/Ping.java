package com.example.springboot.controller.Redis;

import redis.clients.jedis.Jedis;

public class Ping {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("39.101.184.47",6379);
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
    }
}
