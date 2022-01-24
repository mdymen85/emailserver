package com.bmc.emailserver.domain.repository;

import com.bmc.emailserver.domain.User;

public interface IUserRepository {
	
	public User loadUserEmails(String username);
	
}
