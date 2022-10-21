package com.example.demo.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
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
	private UtilMapper utilMapper;
	
	@Autowired
	private UserService userService;
	
	public UserInformationRespone getUserInfo(String email) {
		return utilMapper.convertToEntity(userService.getCartByUser(email), UserInformationRespone.class);
	}
	public List<CartItemRespone> getCart(String email) {
		List<CartItemRespone> set=userService.getCartByUser(email).getCartDetails()
				.stream().map(item->converCartToRepone(item)).collect(Collectors.toList());
		
		
		return utilMapper.convertToResponseList(set, CartItemRespone.class);
		
	}
	public CartItemRespone converCartToRepone(CartDetail cartItem) {
		return CartItemRespone.builder()
								.fisrtPrice(cartItem.getFisrtPrice())
								.finalPrice(cartItem.getFinalPrice())
								.FormatBonusPrice(cartItem.getProductFormat().getFormat().getBonusPrice())
								.formatName(cartItem.getProductFormat().getFormat().getFormatName())
								.imgUrl(cartItem.getProductFormat().getProduct().getImgUrl())
								.productName(cartItem.getProductFormat().getProduct().getProductName())
								.quantity(cartItem.getQuantity()).build();
	}
	
}
