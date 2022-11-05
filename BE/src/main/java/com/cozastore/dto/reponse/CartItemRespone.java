package com.cozastore.dto.reponse;

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
public class CartItemRespone {
	private int productId;
	private int formatId;
	private String productName;
	private String formatName;
	private String imgUrl;
	private int quantity;
	private double firstPrice;
	private double finalPrice;
}
