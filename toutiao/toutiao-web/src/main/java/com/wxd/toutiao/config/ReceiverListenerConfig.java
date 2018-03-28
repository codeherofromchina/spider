package com.wxd.toutiao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * Created by wangxiaodan on 2018/3/28.
 */
@Configuration
public class ReceiverListenerConfig {
    public static final Logger LOGGER = LoggerFactory.getLogger(ReceiverListenerConfig.class);


    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,
                                            MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("toutiaolistener"));
        return container;
    }

    /*
    * 将Receiver注册为一个消息监听器，并指定消息接收的方法（receiveMessage）
    * 如果不指定消息接收的方法，消息监听器会默认的寻找Receiver中的handleMessage这个方法作为消息接收的方法
    */
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    /*
    * Receiver实例
    */
    @Bean
    Receiver receiver(){
        return new Receiver();
    }
}
