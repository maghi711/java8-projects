package com.aadavan.redis;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.cluster.RedisClusterClient;
import com.lambdaworks.redis.cluster.api.StatefulRedisClusterConnection;
import com.lambdaworks.redis.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import com.lambdaworks.redis.cluster.api.sync.RedisAdvancedClusterCommands;

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

    static void redisClusterInit() {
        RedisURI redisUri = RedisURI.Builder.redis("localhost")
                .withPassword("authentication").build();
        RedisClusterClient clusterClient = RedisClusterClient
                .create(redisUri);
        StatefulRedisClusterConnection<String, String> connection
                = clusterClient.connect();
        RedisAdvancedClusterCommands<String, String> syncCommands = connection
                .sync();
        RedisAdvancedClusterAsyncCommands<String, String> asyncCommands = connection.async();
    }

    static void redisClusterClose() {

    }
}
