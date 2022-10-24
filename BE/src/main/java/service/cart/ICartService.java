package service.cart;

import dto.reponse.CartRespone;
import dto.request.CartItemRequest;
import entity.Cart;
import entity.ManytoManyID.CartProductFormatID;

public interface ICartService {
	public CartRespone getCart(String email);
	
	public String deleteAllCartItem(Cart cart);
	
	public String deleteCartItemById(CartItemRequest cartItemRequest);
	
	public String addProductToCart(CartItemRequest cartItemRequest,String email);
	
}
