package com.cozastore.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequest {
	
	@NotNull(message="Id product cannot null")
	private int productId;
	
	@NotBlank(message="Content cannot be empty")
	private String content;
	
	@NotNull(message="Rating start cannot be null")
	@Min(value=1,message="Rating cannot smaller than 1")
	@Max(value=5,message="Rating cannot larger than 5")
	private int rating;
	
}
