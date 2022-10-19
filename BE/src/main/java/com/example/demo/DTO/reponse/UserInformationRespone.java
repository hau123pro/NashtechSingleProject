package com.example.demo.DTO.reponse;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserInformationRespone {
	private String email;
	
	private Date dateOfBirth;
	
	private String phone;
}
