package com.example.demo.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		if(user.getPassword()!=null&&!user.getPassword().equals(password2)) {
			throw new BadRequestException("Password not match!");
		}
		if(userRepository.findUserByEmail(user.getEmail()).isPresent())
		{
			throw new BadRequestException("Email is already used.");
		}
		user.setStatus("Active");
		user.setRoles(Role.USER);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		long millis=System.currentTimeMillis();   
	    // creating a new object of the class Date  
	    java.sql.Date date = new java.sql.Date(millis);  
	    user.setDateCreate(date);
	    user.setDateOfBirth(date);
	    user.setPhone("");
		userRepository.save(user);
		return "User successfully registered.";
	}

	@Override
	public String changePassword(String email, String password, String password2) {
		// TODO Auto-generated method stub
		
		if (password != null && !password.equals(password2)) {
            throw new BadRequestException("Passwords do not match.");
        }
		 User user = userRepository.findUserByEmail(email)
	                .orElseThrow(()->new UsernameNotFoundException("User not found"));
		 user.setPassword(password2);
		 userRepository.save(user);
		return "Password successfully changed!";
	}

}
