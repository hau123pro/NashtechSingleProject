package service.cart;

import dto.request.CartItemRequest;
import entity.Cart;
import entity.ManytoManyID.CartProductFormatID;

public interface ICartService {
	public Cart getCart(String email);
	
	public String deleteAllCartItem(Cart cart);
	
	String deleteCartItemById(CartItemRequest cartItemRequest);
}
