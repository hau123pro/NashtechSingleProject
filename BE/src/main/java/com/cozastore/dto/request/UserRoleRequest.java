package com.cozastore.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cozastore.utils.constant.Role;
import com.cozastore.utils.constant.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserRoleRequest {
	
	@NotBlank(message="Email cannot be empty")
	private String email;
	
	@NotNull(message="Role cannot be empty")
	private Role role;
	
}
