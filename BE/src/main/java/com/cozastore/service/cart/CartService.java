package com.cozastore.service.cart;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.CartItemRespone;
import com.cozastore.dto.reponse.CartRespone;
import com.cozastore.dto.request.CartItemRequest;
import com.cozastore.entity.Cart;
import com.cozastore.entity.CartDetail;
import com.cozastore.entity.ProductFormat;
import com.cozastore.entity.Sale;
import com.cozastore.entity.User;
import com.cozastore.entity.ManytoManyID.CartProductFormatID;
import com.cozastore.entity.ManytoManyID.ProductFormatID;
import com.cozastore.exception.BadRequestException;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.CartMapper;
import com.cozastore.repository.cart.ICartDetailRepository;
import com.cozastore.repository.cart.ICartRepository;
import com.cozastore.repository.productformat.IProductFormatRepository;
import com.cozastore.repository.sale.SaleRepository;
import com.cozastore.repository.user.IUserRepository;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.SuccessString;

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
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
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
		for (CartDetail cartDetail : cart.getCartDetails()) {
			cartDetailRepository.deleteById(cartDetail.getId());
		}
		return "Delete complete";
	}

	@Override
	public String deleteCartItemById(CartItemRequest cartItemRequest) {
		Cart cart = cartRepository.findById(cartItemRequest.getCartID())
				.orElseThrow(() -> new NotFoundException(ErrorString.CART_NOT_FOUND));
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
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
		CartProductFormatID cartProductFormatID = CartProductFormatID.builder().cartID(cartItemRequest.getCartID())
				.formatID(cartItemRequest.getFormatID()).productID(cartItemRequest.getProductID()).build();
		ProductFormatID formatID = ProductFormatID.builder().formatID(cartItemRequest.getFormatID())
				.productID(cartItemRequest.getProductID()).build();
		ProductFormat productFormat = productFormatRepository.findById(formatID)
				.orElseThrow(() -> new NotFoundException(ErrorString.PRODUCT_TYPE_NOT_FOUND));
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
