package com.cozastore.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorInsertRequest {
	@NotBlank(message="Name category cannot be empty")
	private String firstName;
	
	@NotBlank(message="Name category cannot be empty")
	private String lastName;
	
	private String description;
	
	@NotNull(message="Publish Book cannot be empty")
	private int publishBook;
	
	@NotNull(message="quantity sale Book cannot be empty")
	private int quantitySale;
	
	@NotNull(message="Image file cannot null")
	private MultipartFile imgFile;
}
