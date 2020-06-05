package com.yun.cache.redis.base;

import org.springframework.stereotype.Component;

/**
 * @author zhouyaoming
 */
@Component
public class MobileRedis extends BaseCache<Object>{

	public MobileRedis() {
		super(RedisConstant.DEFAULT_INDEX);
	}

	@Override
	public String getKey(String key) {
		return RedisConstant.MOBILE_DEFAULT_KEY + key;
	}

}
