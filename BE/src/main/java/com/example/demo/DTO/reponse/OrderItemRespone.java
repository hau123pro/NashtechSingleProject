package com.example.demo.DTO.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderItemRespone {
	private String productName;
	private String formatName;
	private String imgUrl;
	private int FormatBonusPrice;
	private int quantity;
	private double fisrtPrice;
	private double finalPrice;
}
