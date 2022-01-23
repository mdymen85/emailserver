package com.bmc.emailserver.redis;

public class UserInformationService {

	private static RedisMessagesService redisMessagesService = null;
	
	public static RedisMessagesService getSingletonInstance() {
		if (redisMessagesService == null) {
			redisMessagesService = new RedisMessagesService();
		}
		return redisMessagesService;
	}
	
}
