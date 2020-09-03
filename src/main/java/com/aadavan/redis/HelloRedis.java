package com.aadavan.redis;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;

public class HelloRedis {
    public static void main(String[] args) {
        RedisURI trident = RedisURI.builder().redis("192.168.105.152", 6381).withPassword("trident").build();
        RedisClient redisClient = RedisClient.create(trident);
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        System.out.println("Connected to Redis");
        String set = connection.sync().set("key", "Hello World");
        System.out.println("set = " + set);

        connection.close();
        redisClient.shutdown();
    }
}
