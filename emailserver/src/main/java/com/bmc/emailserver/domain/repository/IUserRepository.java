package com.bmc.emailserver.domain.repository;

import java.util.List;
import java.util.Set;

import com.bmc.emailserver.domain.User;

public interface IUserRepository {
	
	public User loadUserEmails(String username);
	
}
