package com.bmc.emailserver.infraestructure;

import java.util.Set;

import com.bmc.emailserver.domain.Email;
import com.bmc.emailserver.domain.User;
import com.bmc.emailserver.domain.repository.IUserRepository;
import com.bmc.emailserver.domain.repository.UserEmailEntity;


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
		
		var yahoo = UserEmailEntity.builder()
				.email("martin.dymenstein@yahoo.com")
				.password("oejtgdmwxgjarjnp")
				.username("admin")
				.build();

		var google = UserEmailEntity.builder()
				.email("martin.dymenstein@gmail.com")
				.password("")
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
