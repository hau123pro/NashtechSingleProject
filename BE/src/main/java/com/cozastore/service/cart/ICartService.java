package com.cozastore.service.cart;

import com.cozastore.dto.reponse.CartRespone;
import com.cozastore.dto.request.CartItemRequest;
import com.cozastore.entity.Cart;
import com.cozastore.entity.ManytoManyID.CartProductFormatID;

public interface ICartService {
	public CartRespone getCart(String email);
	
	public String deleteAllCartItem(Cart cart);
	
	public String deleteCartItemById(CartItemRequest cartItemRequest);
	
	public String addProductToCart(CartItemRequest cartItemRequest,String email);
	
}
