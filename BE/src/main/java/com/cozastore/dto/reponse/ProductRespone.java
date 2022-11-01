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
public class ProductRespone {
	
	private int Id;
	
	private String productName;
	
	private String description;
	
	private String imgUrl;
	
	private int averageRating;
	
	private double maxPrice;
	
	private double minPrice;
	
	private AuthorResponse authorResponse;
	
	private List<ReviewRespone> reviewRespones;
	
	private List<FormatProductResponse> formatRespones;
	
	private List<CategoryRespone> categoryRespones;
	
	private PageResponse pageResponse;
	
}
