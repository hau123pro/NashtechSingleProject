package com.cozastore.service.auth;

import com.cozastore.dto.auth.LoginResponse;

public interface IAuthService {

	public LoginResponse auth(String email, String password);

	public String logout();

}
