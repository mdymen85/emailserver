package com.bmc.emailserver.mail;

public class UserInformationService {

	private static RedisOrchestrationService redisOrchestrationService = null;
	
	public static RedisOrchestrationService getSingletonInstance() {
		if (redisOrchestrationService == null) {
			redisOrchestrationService = new RedisOrchestrationService();
		}
		return redisOrchestrationService;
	}
	
}
