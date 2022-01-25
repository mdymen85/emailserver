package com.bmc.emailserver.infraestructure;

import java.util.Set;

import com.bmc.emailserver.domain.Email;
import com.bmc.emailserver.domain.User;
import com.bmc.emailserver.infraestructure.repository.IUserRepository;
import com.bmc.emailserver.infraestructure.repository.UserEmailEntity;

import lombok.extern.slf4j.Slf4j;

public class UserRepository implements IUserRepository {

	@Override
	public User loadUserEmails(String username) {
		Set<UserEmailEntity> userEmailsEntity = this.findEmailsByUsername(username);
		
		var user = User
					.builder()
					.username(username)
					.build();
		
		userEmailsEntity
			.stream()
			.forEach(uee -> {				
				var email = toEmail(user, uee);
				user.addEmail(email);
			});
		
		return user;
	}
	
	private Email toEmail(User user, UserEmailEntity userEmailEntity) {
		
		var email = Email
					.builder()
					.email(userEmailEntity.getEmail())
					.password(userEmailEntity.getPassword())
					.user(user)
					.build();
		
		return email;
		
	}
	
	private Set<UserEmailEntity> findEmailsByUsername(String username) {
		
		//TODO: The correct way is to save this passwords encrypted and decrypt when need to create the email session
		
		var yahoo = UserEmailEntity.builder()
				.email("martin.dymenstein@yahoo.com")
				.password("oejtgdmwxgjarjnp")
				.username("admin")
				.build();

		var google = UserEmailEntity.builder()
				.email("martin.dymenstein@gmail.com")
				.password("bcraamspiilnas")
				.username("admin")
				.build();

		var walla = UserEmailEntity.builder()
				.email("martin.dymenstein@walla.com")
				.password("")
				.username("admin")
				.build();

		
		return Set.of(yahoo, google, walla);
	}

	
}
