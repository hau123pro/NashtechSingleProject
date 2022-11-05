package com.cozastore.dto.request;

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
public class CategoryStatusRequest {
	
	@NotNull(message="id cannot be null")
	private int id;
	
	@NotNull(message="Status cannot be empty")
	private Status status;
}
