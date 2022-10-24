package controller;

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

import dto.auth.LoginRequest;
import dto.auth.LoginResponse;
import dto.request.PasswordResetRequest;
import exception.UnauthorizedException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mappers.AuthenticationMapper;
import service.auth.IAuthService;

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
			BindingResult bindingResult) {
		return ResponseEntity.ok(authenticationMapper.passwordReset(passwordReset, bindingResult));
	}

	@GetMapping("/forgot/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable String email) {
		return ResponseEntity.ok(authenticationMapper.sendPasswordResetCode(email));
	}
}
