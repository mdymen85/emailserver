package com.bmc.emailserver.redis;

import org.redisson.api.RMapCache;

import com.bmc.emailserver.domain.User;
import com.bmc.emailserver.domain.repository.IUserRepository;
import com.bmc.emailserver.infraestructure.UserRepository;

public class RedisMessagesService {

	private static String CACHE_NAME = "EMAIL_SERVER";
	
	private IUserRepository userRepository = new UserRepository();
	
	public User loadUserInformation(String username) {
		
		var redisClient = RedisSingleton.getRedisInstance().redisson();
		
		RMapCache<String, User> mapUser = redisClient.getMapCache(CACHE_NAME);
		var cachedUser = mapUser.get(username);	
		
		if (cachedUser == null) {						
			var user = this.userRepository.loadUserEmails(username);
			mapUser.put(CACHE_NAME, user);
		}
		
		return cachedUser;
	}
	
}
