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
	private Integer categoryId;
//	private int 
	private Integer formatId;
	
	private Integer authorId;
	
	private Double firstPrice;
	
	private Double finalPrice;
	
}
