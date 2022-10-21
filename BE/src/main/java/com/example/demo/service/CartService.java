package com.example.demo.service;

import com.example.demo.entity.Cart;

public interface CartService {
	public Cart getCart(String email);
	public String deleteCart(Cart cart);
}
