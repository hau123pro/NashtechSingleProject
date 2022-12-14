package com.cozastore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.request.RegistrationRequest;
import com.cozastore.mappers.AuthenticationMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/registration")
public class RegisterationController {

	@Autowired
	private AuthenticationMapper authenticationMapper;

	@PostMapping
	public ResponseEntity<String> registration(@Valid @RequestBody RegistrationRequest user,
			BindingResult bindingResult) {
		return ResponseEntity.ok(authenticationMapper.registerUser(user, bindingResult));
	}

}
