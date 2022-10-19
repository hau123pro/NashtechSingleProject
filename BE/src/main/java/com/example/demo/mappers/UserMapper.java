package com.example.demo.mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.Order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.DTO.reponse.CartItemRespone;
import com.example.demo.DTO.reponse.OrderRespone;
import com.example.demo.DTO.reponse.UserInformationRespone;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartDetail;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Orders;
import com.example.demo.exception.BadRequestException;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserService userService;
	
	public UserInformationRespone getUserInfo(String email) {
		return mapper.map(userService.getUserByEmail(email), UserInformationRespone.class);
	}
	public List<CartItemRespone> getCart(String email) {
		Set<CartDetail> list=userService.getCartByUser(email).getCartDetails();
		
		return list.stream()
			.map(item->converCartToRepone(item))
			.collect(Collectors.toList());
		
	}
	public CartItemRespone converCartToRepone(CartDetail cartItem) {
		CartItemRespone respone=new CartItemRespone();
		respone.setFinalPrice(cartItem.getFisrtPrice());
		respone.setFisrtPrice(cartItem.getFinalPrice());
		respone.setFormatBonusPrice(cartItem.getProductFormat().getFormat().getBonusPrice());
		respone.setFormatName(cartItem.getProductFormat().getFormat().getFormatName());
		respone.setImgUrl(cartItem.getProductFormat().getProduct().getImgUrl());
		respone.setProductName(cartItem.getProductFormat().getProduct().getProductName());
		respone.setQuantity(cartItem.getQuantity());
		return respone;
	}
	
}
