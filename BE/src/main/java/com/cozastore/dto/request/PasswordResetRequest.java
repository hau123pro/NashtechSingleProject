package com.cozastore.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetRequest {
		@NotBlank(message="email cant not empty")
	 	private String email;

	    @Size(min = 6, max = 16, message = "The password must be between 6 and 16 characters long")
	    private String password;

	    @Size(min = 6, max = 16, message = "The password confirmation must be between 6 and 16 characters long")
	    private String password2;
}
