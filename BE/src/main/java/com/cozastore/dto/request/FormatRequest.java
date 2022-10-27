package com.cozastore.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cozastore.utils.constant.Status;

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
public class FormatRequest {
	
	@NotNull(message="id category cannot be null")
	private int Id;
	
	@NotBlank(message="Name category cannot be empty")
	private String formatName;
	
	private String description;
	
	@NotNull(message="Bonus Price category cannot be null")
	private int bonusPrice;
	
}
