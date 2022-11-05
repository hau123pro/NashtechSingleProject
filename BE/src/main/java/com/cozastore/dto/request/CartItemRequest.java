package com.cozastore.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {
	
	@NotNull(message="Product Id cannot empty")
	private int productId;
	
	@NotNull(message="Format Id cannot empty")
	private int formatId;
	
	@NotNull(message="Quantity cannot empty")
	private int quantity;

}
