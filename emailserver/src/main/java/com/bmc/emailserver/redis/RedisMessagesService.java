package com.bmc.emailserver.redis;

import com.bmc.emailserver.domain.User;
import com.bmc.emailserver.domain.repository.IUserRepository;
import com.bmc.emailserver.infraestructure.UserRepository;

public class RedisMessagesService {
	
	private IUserRepository userRepository = new UserRepository();
	
	public User loadUserInformation(String username) {		
		return this.userRepository.loadUserEmails(username);
	}

}
