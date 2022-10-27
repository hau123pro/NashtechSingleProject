package com.cozastore.dto.reponse;

import java.sql.Date;

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
	private int Id;
	
	private String authorName;
	
	private String description;
	
	private int publishBook;
	
	private int quantitySale;
	
	private String imgUrl;
}
