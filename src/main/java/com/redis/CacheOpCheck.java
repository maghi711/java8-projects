package com.redis;

import com.aadavan.redis.RedisSource;
/*
import com.enstage.eguard.common.ObjectAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
*/
import com.google.common.collect.Lists;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.api.sync.RedisCommands;
import com.lambdaworks.redis.cluster.RedisClusterClient;
import com.lambdaworks.redis.cluster.api.StatefulRedisClusterConnection;
import com.lambdaworks.redis.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import com.lambdaworks.redis.cluster.api.sync.RedisAdvancedClusterCommands;
import com.lambdaworks.redis.cluster.models.partitions.RedisClusterNode;
import com.lambdaworks.redis.cluster.pubsub.RedisClusterPubSubAdapter;
import com.lambdaworks.redis.cluster.pubsub.RedisClusterPubSubListener;
import com.lambdaworks.redis.cluster.pubsub.StatefulRedisClusterPubSubConnection;
import com.lambdaworks.redis.cluster.pubsub.api.sync.RedisClusterPubSubCommands;
import com.lambdaworks.redis.pubsub.RedisPubSubListener;
import com.lambdaworks.redis.pubsub.StatefulRedisPubSubConnection;
import com.lambdaworks.redis.pubsub.api.sync.RedisPubSubCommands;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CacheOpCheck {
    private static final Logger logger = Logger.getLogger(CacheOpCheck.class.getName());
    //private static RedisClient client;
    private static RedisClusterClient client;
    private static StatefulRedisClusterConnection<String, String> connection = null;
    private static StatefulRedisClusterPubSubConnection<String, String> pubSubConnection = null;
    //private static StatefulRedisConnection<String, String> connection = null;

    public static void init(List<RedisSource> redisSourceList, int timeOutInMilliSec){
        Set<RedisURI> listOfHosts = new HashSet<>();
        for(RedisSource redisSource : redisSourceList){
            listOfHosts.add(RedisURI.builder().redis(redisSource.getIp(), redisSource.getPort()).withPassword(redisSource.getPassword()).build());
        }
        client = RedisClusterClient.create(listOfHosts);
        pubSubConnection = client.connectPubSub();//StatefulRedisPubSubConnection // 4.2.0
        //StatefulRedisClusterPubSubConnection // 4.5.0
        pubSubConnection.setTimeout(timeOutInMilliSec, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args){
        RedisSource redisSource = null;

        List<RedisSource> listRedisSource = new ArrayList<>();

        redisSource = new RedisSource();
        redisSource.setIp("localhost");
        redisSource.setPort(6380);
        redisSource.setDuration(100000);
        redisSource.setPassword("trident");

        listRedisSource.add(redisSource);

        redisSource = new RedisSource();
        redisSource.setIp("localhost");
        redisSource.setPort(6381);
        redisSource.setDuration(100000);
        redisSource.setPassword("trident");

        listRedisSource.add(redisSource);

        redisSource = new RedisSource();
        redisSource.setIp("localhost");
        redisSource.setPort(6379);
        redisSource.setDuration(100000);
        redisSource.setPassword("trident");

        listRedisSource.add(redisSource);

        init(listRedisSource,10000);

        /*
        List<String> keys = connection.sync().keys("*");
        int index = 1;

        for (String key : keys) {
            //String value = connection.sync().get(key);
            //System.out.println(index + ". " + key + " - " + value);
            //String value = connection.sync().get(key);
            System.out.println(index + ". " + key );
            index++;

            if(index == 100){
                break;
            }
        }
        System.out.println("Number of Keys:"+keys.size());
        */
        pubSubConnection.setNodeMessagePropagation(true);
        final RedisClusterPubSubCommands<String, String> sync = pubSubConnection.sync();
        //sync.masters().commands().pubsubChannels("__keyspace@0__:*");
        sync.masters().commands().psubscribe("__keyevent@*:*");
        pubSubConnection.addListener(
                new RedisPubSubListener<String, String>() {
                    @Override
                    public void message(String channel, String message) {

                    }

                    @Override
                    public void message(String pattern, String channel, String message) {

                    }

                    @Override
                    public void subscribed(String channel, long count) {

                    }

                    @Override
                    public void psubscribed(String pattern, long count) {

                    }

                    @Override
                    public void unsubscribed(String channel, long count) {

                    }

                    @Override
                    public void punsubscribed(String pattern, long count) {

                    }
                }
        );
        /*
        pubSubConnection.addListener(
                new RedisClusterPubSubListener<String, String>() {
                    @Override
                    public void message(RedisClusterNode node, String channel, String message) {
                        System.out.println("message = " + message);
                    }

                    @Override
                    public void message(RedisClusterNode node, String pattern, String channel, String message) {
                        System.out.println("pattern" + pattern + "channel" + channel + " message = " + message);
                        final String message1 = sync.get("message");
                        System.out.println("Value = " + message1);
                    }

                    @Override
                    public void subscribed(RedisClusterNode node, String channel, long count) {
                        System.out.println("subscribed = " + node);
                    }

                    @Override
                    public void psubscribed(RedisClusterNode node, String pattern, long count) {
                        System.out.println("psubscribed on node " + node + " on pattern " + pattern);
                    }

                    @Override
                    public void unsubscribed(RedisClusterNode node, String channel, long count) {
                        System.out.println("unsubscribed = " + channel);
                    }

                    @Override
                    public void punsubscribed(RedisClusterNode node, String pattern, long count) {
                        System.out.println("punsubscribed = " + pattern);
                    }
                }
        );

         */
        while (true) {
            // just to wait for listening, remove once done.
        }
    }

}
