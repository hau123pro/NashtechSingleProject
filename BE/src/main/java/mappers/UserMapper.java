package mappers;

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

import dto.reponse.CartItemRespone;
import dto.reponse.OrderRespone;
import dto.reponse.UserInformationRespone;
import dto.request.CartItemRequest;
import entity.Cart;
import entity.CartDetail;
import entity.OrderDetail;
import entity.Orders;
import entity.ManytoManyID.CartProductFormatID;
import exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import service.user.IUserService;

@Component
@RequiredArgsConstructor
public class UserMapper {

	@Autowired
	private UtilMapper utilMapper;
	
	@Autowired
	private IUserService userService;
	
	public UserInformationRespone getUserInfo(String email) {
		return utilMapper.convertToEntity(userService.getUserByEmail(email), UserInformationRespone.class);
	}
	public List<CartItemRespone> getCart(String email) {
		List<CartItemRespone> set=userService.getCartByUser(email).getCartDetails()
				.stream().map(item->converCartToRepone(item)).collect(Collectors.toList());
		return utilMapper.convertToResponseList(set, CartItemRespone.class);
		
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
}
