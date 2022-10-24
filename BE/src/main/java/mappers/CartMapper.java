package mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.reponse.CartItemRespone;
import dto.reponse.CartRespone;
import entity.Cart;
import entity.CartDetail;
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
								.fisrtPrice(cartItem.getFisrtPrice())
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
