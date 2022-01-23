package com.bmc.emailserver.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedisService {

	protected RedisService() {}	
	
	public RedissonClient redisson() {		
		Config config = new Config();		
		config.useSingleServer().setAddress(this.getUrlRedis());
		return Redisson.create(config);
	}
	
	private String getUrlRedis() {
		StringBuilder sb = new StringBuilder("redis");
		sb.append(":");
		sb.append("//");
		sb.append("localhost");
		sb.append(":");
		sb.append("6379");
				
		return sb.toString();
	}
	
	
	
}
