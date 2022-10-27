package com.cozastore.dto.auth;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
	@NotBlank(message="email cant not empty")
	private String email;
	@NotBlank(message="password cant not empty")
	private String password;
}
