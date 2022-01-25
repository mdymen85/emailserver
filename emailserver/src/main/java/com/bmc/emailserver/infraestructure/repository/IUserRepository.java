package com.bmc.emailserver.infraestructure.repository;

import com.bmc.emailserver.domain.User;

public interface IUserRepository {
	
	public User loadUserEmails(String username);
	
}
