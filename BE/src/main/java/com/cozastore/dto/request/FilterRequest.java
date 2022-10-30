package com.cozastore.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class FilterRequest {
	private int categoryId;
//	private int 
	private int formatId;
	
	private int authorId;
	
	private int priceStart;
	
	private int PriceEnd;
	
}
