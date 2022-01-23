package com.bmc.emailserver.authentication.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.bmc.emailserver.authentication.dto.Login;
import com.bmc.emailserver.authenticationcom.service.ILoginService;
import com.bmc.emailserver.authenticationcom.service.LoginService;

@Path("/login")
@javax.enterprise.context.RequestScoped
public class LoginController {

	private ILoginService loginService = new LoginService();
	
	@POST
	public String createToken(Login login) {
        return this.loginService.createToken(login);
	}

}