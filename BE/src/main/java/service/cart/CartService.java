package service.cart;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dto.reponse.CartItemRespone;
import dto.reponse.CartRespone;
import dto.request.CartItemRequest;
import entity.Cart;
import entity.CartDetail;
import entity.ProductFormat;
import entity.Sale;
import entity.User;
import entity.ManytoManyID.CartProductFormatID;
import entity.ManytoManyID.ProductFormatID;
import exception.BadRequestException;
import mappers.CartMapper;
import repository.cart.ICartDetailRepository;
import repository.cart.ICartRepository;
import repository.productformat.IProductFormatRepository;
import repository.sale.SaleRepository;
import repository.user.IUserRepository;
import utils.constant.ErrorString;
import utils.constant.SuccessString;

@Service
public class CartService implements ICartService {

	@Autowired
	private ICartDetailRepository cartDetailRepository;

	@Autowired
	private ICartRepository cartRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private CartMapper cartMapper;

	@Autowired
	private IProductFormatRepository productFormatRepository;

	@Autowired
	private SaleRepository saleRepository;

	@Override
	public CartRespone getCart(String email) {
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(ErrorString.USER_NOT_FOUND));
		Cart cart = user.getCart();
		if (cart == null)
			throw new BadRequestException(ErrorString.CART_EMPTY);
		List<CartItemRespone> cartItemRespones = cartMapper.convertToCartItemResponse(cart.getCartDetails());
		Set<CartItemRespone> set = new HashSet<>(cartItemRespones);
		CartRespone cartRespone = cartMapper.converToCartResponse(cart, set);
		System.out.println("");
		return cartRespone;
	}

	@Override
	@Transactional
	public String deleteAllCartItem(Cart cart) {
		// TODO Auto-generated method stub
		for (CartDetail cartDetail : cart.getCartDetails()) {
			cartDetailRepository.deleteById(cartDetail.getId());
		}
		return "Delete complete";
	}

	@Override
	public String deleteCartItemById(CartItemRequest cartItemRequest) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findById(cartItemRequest.getCartID())
				.orElseThrow(() -> new BadRequestException(ErrorString.CART_NOT_FOUND));
		for (CartDetail detail : cart.getCartDetails()) {
			if (detail.getId().getCartID() == cartItemRequest.getCartID()
					&& detail.getId().getFormatID() == cartItemRequest.getFormatID()
					&& detail.getId().getProductID() == cartItemRequest.getProductID()) {
				cart.getCartDetails().remove(detail);
			}
		}
		cartRepository.save(cart);
		return SuccessString.CART_ITEM_DELETE_SUCCESS;
	}

	@Override
	public String addProductToCart(CartItemRequest cartItemRequest, String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(ErrorString.USER_NOT_FOUND));
		CartProductFormatID cartProductFormatID = CartProductFormatID.builder().cartID(cartItemRequest.getCartID())
				.formatID(cartItemRequest.getFormatID()).productID(cartItemRequest.getProductID()).build();
		ProductFormatID formatID = ProductFormatID.builder().formatID(cartItemRequest.getFormatID())
				.productID(cartItemRequest.getProductID()).build();
		ProductFormat productFormat = productFormatRepository.findById(formatID)
				.orElseThrow(() -> new BadRequestException(ErrorString.PRODUCT_TYPE_NOT_FOUND));
		if (productFormat.getQuantity() < cartItemRequest.getQuantity())
			throw new BadRequestException(ErrorString.QUANTITY_NOT_ENOUGH);
		Cart cart = cartRepository.findById(cartProductFormatID.getCartID()).orElse(null);
		Date date = Date.valueOf(LocalDate.now());
		if (cart == null) {
			cart = Cart.builder().firstPrice(productFormat.getPrice()).finalPrice(productFormat.getPrice())
					.dateCreate(date).user(user).quantity(cartItemRequest.getQuantity()).build();
			cart = cartRepository.save(cart);
			cartProductFormatID.setCartID(cart.getID());
		}
		productFormat.setQuantity(productFormat.getQuantity() - cartItemRequest.getQuantity());
		CartDetail cartDetail = CartDetail.builder().cart(cart).productFormat(productFormat)
				.firstPrice(productFormat.getPrice()).finalPrice(productFormat.getPrice())
				.quantity(cartItemRequest.getQuantity()).Id(cartProductFormatID).build();
		Set<CartDetail> cartDetails = new HashSet<>();
		cartDetails.add(cartDetail);
		cart.setCartDetails(cartDetails);
		cartRepository.save(cart);
		productFormatRepository.save(productFormat);
		return SuccessString.CART_ITEM_ADD_SUCCESS;
	}

}
