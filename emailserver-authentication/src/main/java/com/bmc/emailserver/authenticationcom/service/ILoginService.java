package com.bmc.emailserver.authenticationcom.service;

import com.bmc.emailserver.authentication.dto.Login;

public interface ILoginService {

	String createToken(Login login);
	
}
