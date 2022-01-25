package com.bmc.emailserver.infraestructure.redis;

public class RedisSingleton {

	private static RedisService redisService;
	
	public static RedisService getRedisInstance() {
		if (redisService == null) {
			redisService = new RedisService();
		}
		return redisService;
	}
	
}
