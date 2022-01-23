package com.bmc.emailserver.domain.repository;

import java.util.List;
import java.util.Set;

public interface IUserRepository {
	
	public Set<UserEmailEntity> findEmailsByUsername(String username);
	
}
