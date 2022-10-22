package com.example.demo.DTO.request;

import javax.persistence.Column;

import com.example.demo.entity.ProductFormat;
import com.example.demo.entity.ManytoManyID.CartProductFormatID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {
	
	private int cartID;
	
	private int productID;
	
	private int formatID;

	
	
}
