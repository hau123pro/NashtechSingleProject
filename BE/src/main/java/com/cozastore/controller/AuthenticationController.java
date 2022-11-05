package com.cozastore.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.auth.LoginRequest;
import com.cozastore.dto.auth.LoginResponse;
import com.cozastore.dto.request.PasswordResetRequest;
import com.cozastore.exception.UnauthorizedException;
import com.cozastore.mappers.AuthenticationMapper;
import com.cozastore.service.auth.IAuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@Tag(name = "Auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationMapper authenticationMapper;

	@Autowired
	private IAuthService authService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest body) {
		try {
			return ResponseEntity.ok(authService.auth(body.getEmail(), body.getPassword()));
		} catch (UnauthorizedException e) {
			return new ResponseEntity<LoginResponse>(new LoginResponse(), HttpStatus.UNAUTHORIZED);
		}

	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout() {
		return ResponseEntity.ok(authService.logout());
	}

	@PutMapping("/changePassword")
	public ResponseEntity<String> updateUserPassword(@Valid @RequestBody PasswordResetRequest passwordReset,
			Principal principal, BindingResult bindingResult) {
		return ResponseEntity.ok(authenticationMapper.passwordReset(passwordReset, principal.getName(), bindingResult));
	}

}
