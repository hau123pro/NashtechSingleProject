package com.cozastore.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderItemRespone {
	private String productName;
	private String formatName;
	private String imgUrl;
	private int quantity;
	private double fisrtPrice;
	private double finalPrice;
}
