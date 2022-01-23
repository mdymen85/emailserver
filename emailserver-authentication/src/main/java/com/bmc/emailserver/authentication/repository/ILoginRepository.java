package com.bmc.emailserver.authentication.repository;

public interface ILoginRepository {

	boolean existUser(String user, String password);
	
}
