package com.bmc.emailserver.infraestructure.redis;

import com.bmc.emailserver.domain.User;
import com.bmc.emailserver.infraestructure.UserRepository;
import com.bmc.emailserver.infraestructure.repository.IUserRepository;

public class RedisMessagesService {
	
	private IUserRepository userRepository = new UserRepository();
	
	public User loadUserInformation(String username) {		
		return this.userRepository.loadUserEmails(username);
	}

}
