package com.bmc.emailserver.domain.mail.utilities;

import org.redisson.api.RMapCache;
import org.redisson.client.RedisConnectionException;
import org.redisson.client.WriteRedisConnectionException;

import com.bmc.emailserver.domain.User;
import com.bmc.emailserver.infraestructure.redis.RedisMessagesService;
import com.bmc.emailserver.infraestructure.redis.RedisSingleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisOrchestrationService {
	
	private static String CACHE_NAME_INFO_USER = "INFO_USER";
	
	private RedisMessagesService redisMessagesService = new RedisMessagesService();
	
	public User loadUserInformation(String username) {
		
		try {
		
			log.info("Starting cache process...");
			
			var redisClient = RedisSingleton.getRedisInstance().redisson();
			
			log.info("Trying to retreive cache information from cache: {}.", CACHE_NAME_INFO_USER);
			
			RMapCache<String, User> mapUser = redisClient.getMapCache(CACHE_NAME_INFO_USER);
			
			var cachedUser = mapUser.get(username);	
			
			if (cachedUser == null) {						
				
				log.info("Cache for user: {} was empty, loading for database to aggregate cache.", username);
				
				var user = this.redisMessagesService.loadUserInformation(username);
				mapUser.put(username, user);
				
				log.info("Information for the user: {} was added in the cache: {}.", user, CACHE_NAME_INFO_USER);
				
				cachedUser = user;
			}
			
			return cachedUser;
		}
		catch (RedisConnectionException re) {
			
			//Trying to be recilient, if Redis its down, we must go always to pick the information in the database
			
			log.error("Redis is down!!!");
			
			return this.redisMessagesService.loadUserInformation(username); 
			
		}
	}
	
}
