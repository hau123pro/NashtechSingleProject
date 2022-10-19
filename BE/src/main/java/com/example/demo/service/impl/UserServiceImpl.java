package com.example.demo.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Orders;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<User> optional=userRepository.findUserByEmail(email);
		User user=optional.orElseThrow(()->new BadRequestException("User not found"));
		
		return user;
	}
	public Cart getCartByUser(String email) {
		Optional<User> optional=userRepository.findUserByEmail(email);
		User user=optional.orElseThrow(()->new BadRequestException("User not found"));
		if(user.getCart()==null) {
			throw new BadRequestException("Not any item in Cart");
		}
		return user.getCart();
	}
	@Override
	public Set<Orders> getOrdersByUser(String email) {
		// TODO Auto-generated method stub
		Optional<User> optional=userRepository.findUserByEmail(email);
		User user=optional.orElseThrow(()->new BadRequestException("User not found"));
		
		return user.getOrders();
	}
}
