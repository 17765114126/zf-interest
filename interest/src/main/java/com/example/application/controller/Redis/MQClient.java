package com.example.application.controller.Redis;

import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.exceptions.JedisConnectionException;
/**
 * Redis的消息订阅/发布 Utils工具类
 * */
class MQClient {
    public static final int MSG_REALTIME = 1;
    public static final int MSG_CACHED = 2;
    public static final int MSG_SERIALIZABLE = 3;
    public static final String NOTIFY_CHANNEL = "ClientNotify";

    private JedisPool pool;
    private boolean exit;
    private JedisPubSub pubsub;

    public MQClient(String ip, int port, JedisPubSub pubsub) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(10 * 1000);
        config.setMaxIdle(1000);
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, ip, port, Protocol.DEFAULT_TIMEOUT, null);
        exit = false;
        this.pubsub = pubsub;
    }

    public boolean publish(String channels, String message, String content) {
        JSONObject obj = new JSONObject();
        boolean ret = false;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (message != null) {
                obj.put("message", message);
            }
            try {
                JSONObject objCon = new JSONObject(content);
                obj.put("content", objCon);
            } catch (JSONException e) {
                obj.put("content", content);
            }
            String[] tmp = channels.split(";");
            for (String channel : tmp) {
                try {
                    if (jedis.publish(channel, obj.toString()) > 0) {
                        ret = true;
                    }
                } catch (Exception e) {
                    break;
                }
            }
        } catch (JSONException e) {
        } finally {
            if (jedis != null){
                jedis.close();
            }
        }

        return ret;
    }

    public boolean clientNotify(String clients, String message, String content, int type) {
        if (type == MSG_REALTIME) {
            return publish(clients, message, content);
        }

        boolean ret = false;

        try {
            JSONObject obj = new JSONObject();
            obj.put("clients", clients);
            obj.put("type", type);
            if (message != null) {
                obj.put("message", message);
            }
            try {
                JSONObject objCon = new JSONObject(content);
                obj.put("content", objCon);
            } catch (JSONException e) {
                obj.put("content", content);
            }

            if (pool.getResource().publish(NOTIFY_CHANNEL, obj.toString()) > 0) {
                ret = true;
            }
        } catch (JSONException e) {
        }

        return ret;
    }

    public boolean setValue(String key, String value) {
        try {
            String response = pool.getResource().set(key, value);
            if (response != null && response.equals("OK")) {
                return true;
            }
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getValue(String key) {
        return pool.getResource().get(key);
    }

    public void subscribe(String... channels) {
        while (!exit) {
            try {
                pool.getResource().subscribe(pubsub, channels);
            } catch (JedisConnectionException e) {
                e.printStackTrace();
                System.out.println("try reconnect");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

