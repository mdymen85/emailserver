package com.bmc.emailserver.authenticationcom.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.json.JSONArray;

import com.bmc.emailserver.authentication.dto.Login;
import com.bmc.emailserver.authentication.repository.ILoginRepository;
import com.bmc.emailserver.authentication.repository.LoginRepository;

public class LoginService implements ILoginService{

	private ILoginRepository loginRepository = new LoginRepository();
	
	@Override
	public String createToken(Login login) {
		
		if (loginRepository.existUser(login.getUser(), login.getPassword())) {
	        String token = JWebToken.createJWT("1", login.getUser(), "", 360000);
			return token;
		}
		
		throw new IllegalArgumentException();
	}

}
