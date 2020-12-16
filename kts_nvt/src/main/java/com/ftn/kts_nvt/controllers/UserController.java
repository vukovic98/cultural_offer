package com.ftn.kts_nvt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.beans.User;
import com.ftn.kts_nvt.services.UserService;
import com.ftn.kts_nvt.dto.ChangePasswordDto;
import com.ftn.kts_nvt.dto.CulturalOfferDTO;
import com.ftn.kts_nvt.dto.UserDTO;
import com.ftn.kts_nvt.dto.UserNameDto;
import com.ftn.kts_nvt.helper.UserMapper;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> users = userService.findAll();
		return new ResponseEntity<>(userMapper.toUserDTOList(users), HttpStatus.OK);
	}

	@GetMapping(value = "/by-page/{pageNum}")
	public ResponseEntity<Page<UserDTO>> getAll(@PathVariable int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, 10);
		Page<User> page = userService.findAll(pageable);
		List<UserDTO> userDTOS = userMapper.toUserDTOList(page.toList());
		Page<UserDTO> pageUserDTOS = new PageImpl<>(userDTOS, page.getPageable(), page.getTotalElements());

		return new ResponseEntity<>(pageUserDTOS, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		System.out.println("getUser with id = " + id);
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		System.out.println("getUser " + user);

		return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
	}

	@RequestMapping(value = "byEmail/{email}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
		User user = userService.findByEmail(email);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		User user;
		try {
			user = userService.create(userMapper.toEntity(userDTO));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.CREATED);
	}

	@GetMapping(value = "/loggedInUser")
	@PreAuthorize("hasAnyRole('ROLE_USER,ROLE_ADMIN')")
	public ResponseEntity<UserDTO> getLoggedInUser() {
		Authentication data = SecurityContextHolder.getContext().getAuthentication();
		String email = data.getName();

		User user = this.userService.findByEmail(email);

		if (user != null) {

			return new ResponseEntity<>(this.userMapper.toDto(user), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
		User user;
		try {
			user = userService.update(userMapper.toEntity(userDTO), id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/editProfile/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> updateUserName(@RequestBody UserNameDto userDTO, @PathVariable Long id) {
		User user;
		try {
			user = userService.update(userDTO, id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_USER,ROLE_ADMIN')")
	public ResponseEntity<UserDTO> changePassword(@RequestBody ChangePasswordDto passDTO) {
		User user;
		Long id;
		// getting user id from current user
		Authentication data = SecurityContextHolder.getContext().getAuthentication();
		String email = data.getName();
		User currentUser = this.userService.findByEmail(email);
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		if (currentUser != null) {
			boolean passMatch = enc.matches(passDTO.getOldPassword(), currentUser.getPassword());
			if(passMatch) {
				id = currentUser.getId();
				try {
					user = userService.changePassword(passDTO, id);
				} catch (Exception e) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}

				return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		try {
			userService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
