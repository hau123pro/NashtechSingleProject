package com.cozastore.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cozastore.dto.reponse.CartItemRespone;
import com.cozastore.dto.reponse.CartRespone;
import com.cozastore.entity.Cart;
import com.cozastore.entity.CartDetail;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CartMapper {
	@Autowired
	private UtilMapper utilMapper;
	
	public List<CartItemRespone> convertToCartItemResponse(Set<CartDetail> cartDetails){
		List<CartItemRespone> list=cartDetails
				.stream().map(item->converCartToRepone(item)).collect(Collectors.toList());
		return utilMapper.convertToResponseList(list, CartItemRespone.class);
	}
	public CartItemRespone converCartToRepone(CartDetail cartItem) {
		return CartItemRespone.builder()
								.cartProductFormatID(cartItem.getId())
								.firstPrice(cartItem.getFirstPrice())
								.finalPrice(cartItem.getFinalPrice())
								.FormatBonusPrice(cartItem.getProductFormat().getFormat().getBonusPrice())
								.formatName(cartItem.getProductFormat().getFormat().getFormatName())
								.imgUrl(cartItem.getProductFormat().getProduct().getImgUrl())
								.productName(cartItem.getProductFormat().getProduct().getProductName())
								.quantity(cartItem.getQuantity()).build();
	}
	public CartRespone converToCartResponse(Cart cart,Set<CartItemRespone> itemRespones) {
		CartRespone cartRespone=utilMapper.convertToResponse(cart, CartRespone.class);
		cartRespone.setItemRespones(itemRespones);
		return cartRespone;
	}
}
