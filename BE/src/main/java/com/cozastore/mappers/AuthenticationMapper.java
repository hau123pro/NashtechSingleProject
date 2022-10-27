package com.cozastore.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.cozastore.dto.request.PasswordResetRequest;
import com.cozastore.dto.request.RegistrationRequest;
import com.cozastore.entity.User;
import com.cozastore.exception.ApiException;
import com.cozastore.exception.BadRequestException;
import com.cozastore.exception.InputFieldException;
import com.cozastore.service.auth.IAuthService;
import com.cozastore.service.user.IUserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private IUserService userService;
	
	public String registerUser( RegistrationRequest registrationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        	System.out.println(bindingResult.getFieldErrors());
            throw new InputFieldException(bindingResult);
        }
        User user = mapper.map(registrationRequest, User.class);
        return userService.registerUser(user, registrationRequest.getPassword2());
    }
	
	public String passwordReset( PasswordResetRequest passwordReset, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return userService.changePassword(passwordReset.getEmail(),passwordReset.getPassword(), passwordReset.getPassword2());
        }
    }
	public String sendPasswordResetCode(String email) {
		
		return null;
	}
}
