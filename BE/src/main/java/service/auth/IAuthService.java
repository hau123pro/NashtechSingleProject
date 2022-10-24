package service.auth;

import dto.auth.LoginResponse;

public interface IAuthService {

	public LoginResponse auth(String email, String password);

	public String logout();

}
