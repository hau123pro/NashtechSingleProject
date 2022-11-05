package com.cozastore.dto.reponse;

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
public class AuthorResponse {
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String description;
	
	private int publishBook;
	
	private int quantitySale;
	
	private String imgUrl;
}
