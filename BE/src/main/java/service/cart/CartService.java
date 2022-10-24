package service.cart;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.request.CartItemRequest;
import entity.Cart;
import entity.CartDetail;
import entity.ManytoManyID.CartProductFormatID;
import exception.BadRequestException;
import repository.cart.ICartDetailRepository;
import repository.cart.ICartRepository;
import utils.constant.ErrorString;
import utils.constant.SuccessString;

@Service
public class CartService implements ICartService {

	@Autowired
	private ICartDetailRepository iCartDetailRepository;

	@Autowired
	private ICartRepository iCartRepository;

	@Override
	public Cart getCart(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public String deleteAllCartItem(Cart cart) {
		// TODO Auto-generated method stub
		for (CartDetail cartDetail : cart.getCartDetails()) {
			iCartDetailRepository.deleteById(cartDetail.getId());
		}
		return "Delete complete";
	}

	@Override
	public String deleteCartItemById(CartItemRequest cartItemRequest) {
		CartProductFormatID cartProductFormatID = new CartProductFormatID(cartItemRequest.getCartID(),
				cartItemRequest.getProductID(), cartItemRequest.getFormatID());
		// TODO Auto-generated method stub
		CartDetail cartDetail = iCartDetailRepository.findById(cartProductFormatID)
				.orElseThrow(() -> new BadRequestException(ErrorString.ITEM_CART_NOT_FOUND));
		iCartDetailRepository.deleteById(cartDetail.getId());
		System.out.println(cartItemRequest.getProductID());
		return SuccessString.CART_ITEM_DELETE;
	}

}
