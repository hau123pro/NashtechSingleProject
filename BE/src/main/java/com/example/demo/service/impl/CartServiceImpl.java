package com.example.demo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.request.CartItemRequest;
import com.example.demo.constant.ErrorString;
import com.example.demo.constant.SuccessString;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartDetail;
import com.example.demo.entity.ManytoManyID.CartProductFormatID;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CartDetailRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDetailRepository cartDetailRepository;
	
	@Autowired 
	private CartRepository cartRepository;
	
	@Override
	public Cart getCart(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public String deleteAllCartItem(Cart cart) {
		// TODO Auto-generated method stub
		for(CartDetail cartDetail:cart.getCartDetails()) {
			cartDetailRepository.deleteById(cartDetail.getId());
		}
		return "Delete complete";
	}

	@Override
	public String deleteCartItemById(CartItemRequest cartItemRequest) {
		CartProductFormatID cartProductFormatID=new CartProductFormatID(cartItemRequest.getCartID(),cartItemRequest.getProductID(), cartItemRequest.getFormatID());
		// TODO Auto-generated method stub
		CartDetail cartDetail=cartDetailRepository.findById(cartProductFormatID)
													.orElseThrow(()->new BadRequestException(ErrorString.ITEM_CART_NOT_FOUND));
		cartDetailRepository.deleteById(cartDetail.getId());
		System.out.println(cartItemRequest.getProductID());
		return SuccessString.CART_ITEM_DELETE;
	}

	

	

}
