package com.ftn.kts_nvt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.beans.User;
import com.ftn.kts_nvt.dto.ChangePasswordDto;
import com.ftn.kts_nvt.dto.UserNameDto;
import com.ftn.kts_nvt.repositories.UserRepository;

@Service
public class UserService implements ServiceInterface<User> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Page<User> findAll(Pageable pagable) {
		return userRepository.findAll(pagable);
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public User findByEmail(String email) {

		return userRepository.findByEmail(email);

	}

	@Override
	public User create(User entity) throws Exception {
		if (userRepository.findByEmail(entity.getEmail()) != null) {
			throw new Exception("User with given email address already exists");
		}
		return userRepository.save(entity);
	}

	@Override
	public void delete(Long id) throws Exception {
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser == null) {
			throw new Exception("User with given id doesn't exist");
		}
		userRepository.delete(existingUser);
	}

	@Override
	public User update(User entity, Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public User changePassword(ChangePasswordDto entity, Long id) throws Exception {
		User user = userRepository.findById(id).orElse(null);
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		if (user != null) {
			user.setPassword(enc.encode(entity.getNewPassword()));
		} else {
			throw new Exception("User with given id doesn't exist.");
		}

		return userRepository.save(user);
	}

	public User update(UserNameDto entity, Long id) throws Exception {
		User user = userRepository.findById(id).orElse(null);

		if (user != null) {
			user.setFirstName(entity.getFirstName());
			user.setLastName(entity.getLastName());

		} else {
			throw new Exception("User with given id doesn't exist.");
		}

		return userRepository.save(user);
	}

}
