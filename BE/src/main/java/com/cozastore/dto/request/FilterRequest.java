package com.cozastore.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterRequest {
	private Integer categoryId;
//	private int 
	private Integer formatId;
	
	private Integer authorId;
	
	private Double firstPrice;
	
	private Double finalPrice;
	
}
