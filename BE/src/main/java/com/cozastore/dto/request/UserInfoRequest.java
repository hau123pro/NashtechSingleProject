package com.cozastore.dto.request;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cozastore.utils.constant.Role;
import com.cozastore.utils.constant.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRequest {
	@NotBlank(message="Name cannot be empty")
	private String name;

	@NotBlank(message="Date cannot be empty")
	private Date dateOfBirth;

	@NotBlank(message="Phone cannot be empty")
	private String phone;	
	
}
