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
public class CartItemIdRequest extends CartItemRequest{
	@NotNull(message="Cart Id cannot empty")
	private int cartId;
}
