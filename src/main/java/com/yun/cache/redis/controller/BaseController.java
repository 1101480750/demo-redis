package com.yun.cache.redis.controller;

import com.yun.cache.redis.base.RedisListenerHandle;
import com.yun.cache.redis.base.WebRedis;
import com.yun.cache.redis.dto.RedisMsgDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 控制器
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @date 2020/6/2 17:49
 */
@Slf4j
@RestController
public class BaseController {

    @Autowired
    private WebRedis webRedis;

    @Autowired
    private RedisListenerHandle redisListenerHandle;
    @RequestMapping("/addMessage")
    @ResponseBody
    public String addMessage(String data) {

        log.info("nlsData", data);
        RedisMsgDto redisMsgDto = new RedisMsgDto("254", "25", "254", "2564","224", "zhouyaoming");
        redisListenerHandle.publicToWorkbench(redisMsgDto);
        webRedis.setCache("1101480750".concat(UUID.randomUUID().toString().replace("-", "")), redisMsgDto, 90000);
        return "ok";
    }

    @RequestMapping("/addMobileMessage")
    @ResponseBody
    public String addMobileMessage(String data) {

        log.info("nlsData", data);
        RedisMsgDto redisMsgDto = new RedisMsgDto("mobile", "25", "254", "2564","224", "zhouyaoming");
        redisListenerHandle.publicToMobile(redisMsgDto);
        return "ok";
    }



    public static void main(String[] args) {
        String name = " zhou yao ming ";
        String s = name.trim();
        System.out.println("ss:" + s);
    }
}
