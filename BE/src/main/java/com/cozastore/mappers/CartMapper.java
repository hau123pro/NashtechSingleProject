package com.cozastore.mappers;

import java.util.ArrayList;
import java.util.List;

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

	public List<CartItemRespone> convertToCartItemResponse(List<CartDetail> cartDetails) {
		List<CartItemRespone> itemRespones = new ArrayList<>();
		for (CartDetail cartDetail : cartDetails) {
			CartItemRespone cartItemRespone = converCartToRepone(cartDetail);
			itemRespones.add(cartItemRespone);
		}
		return itemRespones;
	}

	public CartItemRespone converCartToRepone(CartDetail cartItem) {

		CartItemRespone cartItemRespone = CartItemRespone.builder().productId(cartItem.getId().getProductId())
				.formatId(cartItem.getId().getFormatId()).firstPrice(cartItem.getFirstPrice())
				.finalPrice(cartItem.getFinalPrice())
				.formatName(cartItem.getProductFormat().getFormat().getFormatName())
				.imgUrl(cartItem.getProductFormat().getProduct().getImgUrl())
				.productName(cartItem.getProductFormat().getProduct().getProductName()).quantity(cartItem.getQuantity())
				.build();
		System.out.println("vào");
		return cartItemRespone;
	}

	public CartRespone converToCartResponse(Cart cart, List<CartItemRespone> itemRespones) {
		CartRespone cartRespone = utilMapper.convertToResponse(cart, CartRespone.class);
		cartRespone.setItemRespones(itemRespones);
		return cartRespone;
	}
}
