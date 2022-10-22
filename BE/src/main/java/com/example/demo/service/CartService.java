package com.example.demo.service;

import com.example.demo.DTO.request.CartItemRequest;
import com.example.demo.entity.Cart;
import com.example.demo.entity.ManytoManyID.CartProductFormatID;

public interface CartService {
	public Cart getCart(String email);
	
	public String deleteAllCartItem(Cart cart);
	
	String deleteCartItemById(CartItemRequest cartItemRequest);
}
