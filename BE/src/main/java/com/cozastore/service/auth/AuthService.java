package com.cozastore.service.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cozastore.dto.auth.LoginResponse;
import com.cozastore.entity.User;
import com.cozastore.exception.UnauthorizedException;
import com.cozastore.repository.user.IUserRepository;
import com.cozastore.utils.JwtTokenUtil;

@Service
public class AuthService implements IAuthService {

	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public LoginResponse auth(String email, String password) {

		Optional<User> user = userRepository.findUserByEmail(email);
		if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
			String token = jwtTokenUtil.generateToken(user.get());
			return new LoginResponse(token);
		}
		 throw new  UnauthorizedException (); 
	}

	public String logout() {
		return "logout";
	}

}
