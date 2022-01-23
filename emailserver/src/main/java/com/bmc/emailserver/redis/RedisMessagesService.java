package com.bmc.emailserver.redis;

import org.redisson.api.RMapCache;

import com.bmc.emailserver.domain.User;
import com.bmc.emailserver.domain.repository.IUserRepository;
import com.bmc.emailserver.dto.MessageDTO;
import com.bmc.emailserver.infraestructure.UserRepository;

public class RedisMessagesService {

	private static String CACHE_NAME = "EMAIL_SERVER";
	
	private IUserRepository userRepository = new UserRepository();
	
	public User loadUserInformation(MessageDTO messageDTO) {
		
		var redisClient = RedisSingleton.getRedisInstance().redisson();
		
		RMapCache<String, User> mapUser = redisClient.getMapCache(CACHE_NAME);
		var cachedUser = mapUser.get(messageDTO.getFrom());	
		
		if (cachedUser == null) {						
			var user = this.userRepository.loadUserEmails(messageDTO.getFrom());
			mapUser.put(CACHE_NAME, user);
		}
		
		return cachedUser;
	}
	
}
