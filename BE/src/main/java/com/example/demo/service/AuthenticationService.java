package com.example.demo.service;

import com.example.demo.entity.User;

public interface AuthenticationService {
	
	  public String registerUser(User user, String password2) ;
	  
	  public String changePassword(String email,String password,String password2);
}
