package com.cozastore.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorInfoRequest {
	
	@NotNull(message="id cannot be null")
	private int Id;
	
	@NotBlank(message="Name category cannot be empty")
	private String firstName;
	
	@NotBlank(message="Name category cannot be empty")
	private String lastName;
	
	private String description;
	
	@NotNull(message="Publish Book cannot be empty")
	private int publishBook;
	
	@NotNull(message="quantity sale Book cannot be empty")
	private int quantitySale;
	
	@NotBlank(message="Image Author cannot be empty")
	private String imgUrl;
	
}
