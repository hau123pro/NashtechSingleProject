package com.cozastore.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.cozastore.dto.request.PasswordResetRequest;
import com.cozastore.dto.request.RegistrationRequest;
import com.cozastore.entity.User;
import com.cozastore.exception.InputFieldException;
import com.cozastore.service.user.IUserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private IUserService userService;

	public String registerUser(RegistrationRequest registrationRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getFieldErrors());
			throw new InputFieldException(bindingResult);
		}
		User user = mapper.map(registrationRequest, User.class);
		return userService.registerUser(user, registrationRequest.getConfirmPassword());
	}

	public String passwordReset(PasswordResetRequest passwordReset,String email, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new InputFieldException(bindingResult);
		} else {
			return userService.changePassword(email, passwordReset.getPassword(),
					passwordReset.getConfirmPassword(),passwordReset.getPasswordOld());
		}
	}

	public String sendPasswordResetCode(String email) {

		return null;
	}
}
