package com.yun.cache.redis.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis订阅频道属性类
 * @author yangzhendong01
 */
@Component
public class RedisListener {

    private static final Logger logger = LoggerFactory.getLogger(RedisListener.class);

    @Value("${redis.channel.msgToWorkbench.topic}")
    private String msgToWorkbenchTopic;

    @Value("${redis.channel.msgToMobile.topic}")
    private String msgToMobileTopic;


    /**
     * redis消息监听器容器
     * 	可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 	通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        List<PatternTopic> topicList = new ArrayList<PatternTopic>();
        // 监听坐席端节点
        topicList.add(new PatternTopic(msgToWorkbenchTopic));
        // 监听移动端节点
        topicList.add(new PatternTopic(msgToMobileTopic));
        container.addMessageListener(listenerAdapter, topicList);
        logger.info("Subscribed Redis channel: " + topicList.toString());
        return container;
    }
}
