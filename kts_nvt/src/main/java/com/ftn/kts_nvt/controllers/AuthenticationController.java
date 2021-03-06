package com.ftn.kts_nvt.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.beans.User;
import com.ftn.kts_nvt.beans.VerificationCode;
import com.ftn.kts_nvt.dto.UserDTO;
import com.ftn.kts_nvt.dto.UserLoginDTO;
import com.ftn.kts_nvt.dto.UserTokenStateDTO;
import com.ftn.kts_nvt.dto.VerificationCodeDTO;
import com.ftn.kts_nvt.helper.RegisteredUserMapper;
import com.ftn.kts_nvt.helper.UserMapper;
import com.ftn.kts_nvt.security.TokenUtils;
import com.ftn.kts_nvt.services.CustomUserDetailsService;
import com.ftn.kts_nvt.services.RegisteredUserService;
import com.ftn.kts_nvt.services.UserService;
import com.ftn.kts_nvt.services.VerificationCodeService;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private RegisteredUserService registeredUserService;

	@Autowired
	private VerificationCodeService verificationCodeService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RegisteredUserMapper regUserMapper;

	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/log-in")
	public ResponseEntity<UserTokenStateDTO> createAuthenticationToken(@RequestBody UserLoginDTO authenticationRequest,
			HttpServletResponse response) {

		try {
			// RegisteredUser u =
			// this.registeredUserService.findOneByEmail(authenticationRequest.getEmail());
			boolean verified = false;

			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), authenticationRequest.getPassword()));

			// Ubaci korisnika u trenutni security kontekst
			System.out.println(authentication.getName());
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Kreiraj token za tog korisnika
			User user = (User) authentication.getPrincipal();
			if (user instanceof RegisteredUser) {
				RegisteredUser temp = (RegisteredUser) user;
				verified = temp.isVerified();
			} else {
				verified = true;
			}

			String email = user.getEmail();
			String jwt = tokenUtils.generateToken(user.getEmail()); // prijavljujemo se na sistem sa email adresom
			int expiresIn = tokenUtils.getExpiredIn();

			// Vrati token kao odgovor na uspesnu autentifikaciju
			return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn, email, verified));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/verify")
	public ResponseEntity<HttpStatus> verifyUser(@RequestBody VerificationCodeDTO dto) {
		RegisteredUser user = this.registeredUserService.findOneByEmail(dto.getUserEmail());

		if (user != null) {
			VerificationCode code = this.verificationCodeService.findCodeByUser(user.getId());

			if (dto.getCode().equalsIgnoreCase(code.getCode())) {
				this.verificationCodeService.deleteCodeForUser(user.getId());

				user.setVerified(true);
				this.registeredUserService.save(user);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/sendCodeAgain")
	public ResponseEntity<HttpStatus> sendCodeAgain(@RequestBody String email) {
		RegisteredUser user = this.registeredUserService.findOneByEmail(email);
		VerificationCode code = this.verificationCodeService.findCodeByUser(user.getId());

		if (user == null || code == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			this.verificationCodeService.sendCode(user, code);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}

	}
	// Endpoint za registraciju novog korisnika

	@PostMapping("/sign-up")
	public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userRequest, Errors er) throws Exception {

		if (!er.hasErrors()) {

			User existUser = this.userService.findByEmail(userRequest.getEmail());
			if (existUser != null) {
				// throw new Exception("Username already exists");
				return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);

			}
			BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
			userRequest.setPassword(enc.encode(userRequest.getPassword()));
			try {
				existUser = registeredUserService.create(regUserMapper.toEntity(userRequest));
				VerificationCode code = new VerificationCode((RegisteredUser) existUser);

				this.verificationCodeService.create(code);
				this.verificationCodeService.sendCode(existUser, code);

			} catch (Exception e) {

				return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(userMapper.toDto(existUser), HttpStatus.CREATED);

		} else {
			return new ResponseEntity<>(er.getAllErrors(), HttpStatus.BAD_REQUEST);

		}
	}

	// U slucaju isteka vazenja JWT tokena, endpoint koji se poziva da se token
	// osvezi
	@PostMapping(value = "/refresh")
	public ResponseEntity<UserTokenStateDTO> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		// String username = this.tokenUtils.getUsernameFromToken(token);
		// User user = (User) this.userDetailsService.loadUserByUsername(username);
		String refreshedToken = tokenUtils.refreshToken(token);
		int expiresIn = tokenUtils.getExpiredIn();

		return ResponseEntity.ok(new UserTokenStateDTO(refreshedToken, expiresIn));
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}

	public static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}

	public AuthenticationController() {
		userMapper = new UserMapper();
	}
}
