package com.ftn.kts_nvt.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ftn.kts_nvt.beans.User;
import com.ftn.kts_nvt.repositories.UserRepository;

@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	UserRepository userRepository;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		User user = userRepository.findByEmail(username);

		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		

		if (enc.matches((String)authentication.getCredentials(), user.getPassword()))
			return user;
		else
			throw new BadCredentialsException("Password incorrect");
			
	}

}
