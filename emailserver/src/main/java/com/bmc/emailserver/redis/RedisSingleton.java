package com.bmc.emailserver.redis;

import org.redisson.api.RedissonClient;

public class RedisSingleton {

	private static RedisService redisService;
	
	public static RedisService getRedisInstance() {
		if (redisService == null) {
			redisService = new RedisService();
		}
		return redisService;
	}
	
}
