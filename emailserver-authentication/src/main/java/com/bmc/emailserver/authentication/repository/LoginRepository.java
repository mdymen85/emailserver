package com.bmc.emailserver.authentication.repository;

public class LoginRepository implements ILoginRepository {

	@Override
	public boolean existUser(String user, String password) {		
		return user.equals("admin") && user.equals("admin");
	}

}
