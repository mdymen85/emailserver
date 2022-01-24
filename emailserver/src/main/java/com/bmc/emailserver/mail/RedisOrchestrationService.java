package com.bmc.emailserver.mail;

import org.redisson.api.RMapCache;

import com.bmc.emailserver.domain.User;
import com.bmc.emailserver.redis.RedisMessagesService;
import com.bmc.emailserver.redis.RedisSingleton;

public class RedisOrchestrationService {
	
	private static String CACHE_NAME_INFO_USER = "INFO_USER";
	
	private RedisMessagesService redisMessagesService = new RedisMessagesService();
	
	public User loadUserInformation(String username) {
		
		var redisClient = RedisSingleton.getRedisInstance().redisson();
		
		RMapCache<String, User> mapUser = redisClient.getMapCache(CACHE_NAME_INFO_USER);
		var cachedUser = mapUser.get(username);	
		
		if (cachedUser == null) {						
			var user = this.redisMessagesService.loadUserInformation(username);
			mapUser.put(username, user);
			cachedUser = user;
		}
		
		return cachedUser;
	}
	
}
