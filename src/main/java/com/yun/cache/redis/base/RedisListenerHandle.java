package com.yun.cache.redis.base;

import com.alibaba.fastjson.JSONObject;
import com.yun.cache.redis.dto.RedisMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * Redis订阅频道处理类
 *
 * @author zhouyaoming
 */
@Component
public class RedisListenerHandle extends MessageListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RedisListenerHandle.class);


    @Value("${redis.channel.msgToWorkbench.topic}")
    private String msgToWorkbenchTopic;

    @Value("${redis.channel.msgToMobile.topic}")
    private String msgToMobileTopic;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 收到监听消息
     *
     * @param message
     * @param bytes
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        logger.info("监听内容：{}", message);
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        //消息内容
        String rawMsg;
        //消息节点
        String topic;
        //消息内容体
        RedisMsgDto msgDto;
        try {
            rawMsg = redisTemplate.getStringSerializer().deserialize(body);
            topic = redisTemplate.getStringSerializer().deserialize(channel);
            logger.info("Received raw message from topic:" + topic + ", raw message content：" + rawMsg);
            msgDto = JSONObject.parseObject(rawMsg, RedisMsgDto.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return;
        }


    }

    /**
     * 发布发送给移动端
     *
     * @param object
     */
    public void publicToMobile(Object object) {
        publicMsg(msgToMobileTopic, object);
    }

    /**
     * 发布给坐席端
     *
     * @param object
     */
    public void publicToWorkbench(Object object) {
        publicMsg(msgToWorkbenchTopic, object);
    }

    /**
     * 发布消息
     *
     * @param msgTo
     * @param object
     */
    public void publicMsg(String msgTo, Object object) {
        try {
            String rawMsg = JSONObject.toJSONString(object);
            logger.info("public message to topic:" + msgTo + ", public message content：" + rawMsg);
            redisTemplate.convertAndSend(msgTo, rawMsg);
        } catch (Exception e) {
            logger.error("发布消息失败" + e.getMessage(), e);
        }
    }

}
