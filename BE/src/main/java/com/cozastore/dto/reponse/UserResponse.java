package com.cozastore.dto.reponse;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;

import com.cozastore.utils.constant.Role;
import com.cozastore.utils.constant.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
	
	private int userId;

	private String email;

	private String name;

	private Date dateOfBirth;

	private String phone;

	private Date dateCreate;

	private Status status;

	private Role roles;
}
