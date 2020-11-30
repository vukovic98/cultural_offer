package com.ftn.kts_nvt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.User;
import com.ftn.kts_nvt.repositories.UserRepository;

@Service
public class UserService implements ServiceInterface<User> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
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
	public User update(User entity, Long id) throws Exception {
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser == null) {
			throw new Exception("User with given id doesn't exist");
		}
		existingUser.setPassword(entity.getPassword());
		return userRepository.save(existingUser);
	}

	@Override
	public void delete(Long id) throws Exception {
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser == null) {
			throw new Exception("User with given id doesn't exist");
		}
		userRepository.delete(existingUser);
	}
}
