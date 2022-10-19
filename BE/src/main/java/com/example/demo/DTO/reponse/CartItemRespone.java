package com.example.demo.DTO.reponse;

import javax.validation.constraints.NotBlank;

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
	private String productName;
	private String formatName;
	private String imgUrl;
	private int FormatBonusPrice;
	private int quantity;
	private double fisrtPrice;
	private double finalPrice;
}
