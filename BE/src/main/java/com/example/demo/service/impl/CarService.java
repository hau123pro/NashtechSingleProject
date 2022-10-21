package com.example.demo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartDetail;
import com.example.demo.repository.CartDetailRepository;
import com.example.demo.service.CartService;

@Service
public class CarService implements CartService{

	@Autowired
	private CartDetailRepository cartDetailRepository;
	
	@Override
	public Cart getCart(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public String deleteCart(Cart cart) {
		// TODO Auto-generated method stub
		for(CartDetail cartDetail:cart.getCartDetails()) {
			cartDetailRepository.deleteById(cartDetail.getId());
		}
		return "Delete complete";
	}

}
