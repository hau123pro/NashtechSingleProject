package com.cozastore.dto.reponse;

import java.util.List;

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
public class ProductBaseResponse {
	
	private int id;
	
	private String productName;
	
	private String description;
	
	private String imgUrl;
	
	private int averageRating;
	
	private Double maxPrice;
	
	private Double minPrice;
	
	private AuthorResponse authorResponse;
}
