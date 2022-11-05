package com.cozastore.service.cart;

import com.cozastore.dto.reponse.CartRespone;
import com.cozastore.dto.request.CartItemIdRequest;
import com.cozastore.dto.request.CartItemRequest;
import com.cozastore.entity.Cart;

public interface ICartService {
	public CartRespone getCart(String email);
	
	public Integer getCountItemCart(String email);
	
	public String deleteAllCartItem(Cart cart);
	
	public String deleteCartItemById(CartItemIdRequest cartItemRequest);
	
	public String addProductToCart(CartItemRequest cartItemRequest,String email);
	
	public void updateCartItem(CartItemIdRequest cartItemRequest);
	
}
