package com.example.demo.service;

import java.util.Set;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Orders;
import com.example.demo.entity.User;

public interface UserService {
	
	public User getUserByEmail(String email);
	public Cart getCartByUser(String email);
	public Set<Orders> getOrdersByUser(String email);
}
