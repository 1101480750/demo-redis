package com.yun.cache.redis.base;

import com.yun.cache.redis.dto.RedisMsgDto;
import org.springframework.stereotype.Component;

@Component
public class WebRedis extends BaseCache<RedisMsgDto> {

    public WebRedis() {
        super(RedisConstant.DEFAULT_INDEX);
    }

    @Override
    public String getKey(String key) {
        return RedisConstant.DEFAULT_KEY + key;
    }

}
