package com.bmc.emailserver.mail.service;

import com.bmc.emailserver.domain.User;
import com.bmc.emailserver.domain.repository.IUserRepository;
import com.bmc.emailserver.dto.MessageDTO;
import com.bmc.emailserver.infraestructure.UserRepository;

public class EMailInformationService {

	private IUserRepository userRepository = new UserRepository();
	
	public User loadUserInformation(MessageDTO messageDTO) {
		
		return null;
	}
	
}
