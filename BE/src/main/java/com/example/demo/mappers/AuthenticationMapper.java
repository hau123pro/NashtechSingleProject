package com.example.demo.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.example.demo.DTO.request.PasswordResetRequest;
import com.example.demo.DTO.request.RegistrationRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.InputFieldException;
import com.example.demo.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	public String registerUser( RegistrationRequest registrationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        	System.out.println(bindingResult.getFieldErrors());
            throw new InputFieldException(bindingResult);
        }
        User user = mapper.map(registrationRequest, User.class);
        return authenticationService.registerUser(user, registrationRequest.getPassword2());
    }
	
	public String passwordReset( PasswordResetRequest passwordReset, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return authenticationService.changePassword(passwordReset.getEmail(),passwordReset.getPassword(), passwordReset.getPassword2());
        }
    }
	public String sendPasswordResetCode(String email) {
		
		return null;
	}
}
