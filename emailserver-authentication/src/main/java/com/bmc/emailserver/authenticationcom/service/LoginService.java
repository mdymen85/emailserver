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
	        long exp = LocalDateTime.now().plusDays(90).toEpochSecond(ZoneOffset.UTC);
	        String token = new JWebToken("1234", new JSONArray("['admin']"), exp, login.getUser()).toString();
			return token;
		}
		
		throw new IllegalArgumentException();
	}

}
