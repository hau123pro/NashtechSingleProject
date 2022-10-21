package com.example.demo.service.impl;

import java.sql.Date;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.ErrorString;
import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	 private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public String registerUser(User user, String password2) {
		if(user.getPassword()!=null&&!user.getPassword().equals(password2)) {
			throw new BadRequestException(ErrorString.PASS_NOT_MATCH);
		}
		if(userRepository.findUserByEmail(user.getEmail()).isPresent())
		{
			throw new BadRequestException(ErrorString.EMAIL_IN_USE);
		}
		user.setStatus("Active");
		user.setRoles(Role.USER);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		long millis=System.currentTimeMillis();   
	    // creating a new object of the class Date  
	    Date date = new Date(millis);  
	    user.setDateCreate(date);
	    user.setDateOfBirth(date);
	    user.setPhone("");
		userRepository.save(user);
		return "User successfully registered.";
	}

	@Override
	public String changePassword(String email, String password, String password2) {
		
		if (password != null && !password.equals(password2)) {
            throw new BadRequestException(ErrorString.PASS_NOT_MATCH);
        }
		 User user = userRepository.findUserByEmail(email)
	                .orElseThrow(()->new UsernameNotFoundException(ErrorString.USER_NOT_FOUND));
		 user.setPassword(password2);
		 userRepository.save(user);
		return "Password successfully changed!";
	}

}
