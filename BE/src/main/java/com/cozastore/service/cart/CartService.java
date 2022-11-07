package com.cozastore.service.cart;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.CartItemRespone;
import com.cozastore.dto.reponse.CartRespone;
import com.cozastore.dto.request.CartItemIdRequest;
import com.cozastore.dto.request.CartItemRequest;
import com.cozastore.entity.Cart;
import com.cozastore.entity.CartDetail;
import com.cozastore.entity.ProductFormat;
import com.cozastore.entity.User;
import com.cozastore.entity.ManytoManyID.CartProductFormatId;
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

	private ICartDetailRepository cartDetailRepository;

	private ICartRepository cartRepository;

	private IUserRepository userRepository;

	private CartMapper cartMapper;

	private IProductFormatRepository productFormatRepository;

	private SaleRepository saleRepository;

	@Autowired
	public CartService(ICartDetailRepository cartDetailRepository, ICartRepository cartRepository,
			IUserRepository userRepository, CartMapper cartMapper, IProductFormatRepository productFormatRepository,
			SaleRepository saleRepository) {
		super();
		this.cartDetailRepository = cartDetailRepository;
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
		this.cartMapper = cartMapper;
		this.productFormatRepository = productFormatRepository;
		this.saleRepository = saleRepository;
	}

	@Override
	public CartRespone getCart(String email) {
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
		Cart cart = user.getCart();
		if (cart == null)
			throw new BadRequestException(ErrorString.CART_EMPTY);
		List<CartItemRespone> cartItemRespones = cartMapper.convertToCartItemResponse(cart.getCartDetails());
		List<CartItemRespone> set = new ArrayList<>(cartItemRespones);
		CartRespone cartRespone = cartMapper.converToCartResponse(cart, set);
		cartRespone.setItemRespones(set);
		cartRespone.setCountItem(cartRepository.findCountItemByCartId(cart.getId()));
		return cartRespone;
	}

	@Override
//	@Transactional
	public String deleteAllCartItem(Cart cart) {
		List<CartDetail> cartDetails = cart.getCartDetails();
//		if(cartDetails!=null)
		cartDetails.clear();
//		cartRepository.delete(cart);
		return "Delete complete";
	}

	@Override
	public String deleteCartItemById(CartItemIdRequest cartItemRequest) {
		Cart cart = cartRepository.findById(cartItemRequest.getCartId())
				.orElseThrow(() -> new NotFoundException(ErrorString.CART_NOT_FOUND));

		if (cart.getCartDetails().size() == 1) {
			CartDetail cartDetail = cart.getCartDetails().get(0);
			cart.getCartDetails().clear();
			cartDetailRepository.delete(cartDetail);
		} else
			for (CartDetail detail : cart.getCartDetails()) {
				if (detail.getId().getCartId() == cartItemRequest.getCartId()
						&& detail.getId().getFormatId() == cartItemRequest.getFormatId()
						&& detail.getId().getProductId() == cartItemRequest.getProductId()) {
					cart.getCartDetails().remove(detail);
					cartDetailRepository.delete(detail);
				}
			}
		return SuccessString.CART_ITEM_DELETE_SUCCESS;
	}

	@Override
	public String addProductToCart(CartItemRequest cartItemRequest, String email) {
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
		ProductFormatID formatID = ProductFormatID.builder().formatID(cartItemRequest.getFormatId())
				.productID(cartItemRequest.getProductId()).build();
		ProductFormat productFormat = productFormatRepository.findById(formatID)
				.orElseThrow(() -> new NotFoundException(ErrorString.PRODUCT_TYPE_NOT_FOUND));
		if (productFormat.getQuantity() < cartItemRequest.getQuantity())
			throw new BadRequestException(ErrorString.QUANTITY_NOT_ENOUGH);
		if (cartItemRequest.getQuantity() <= 0)
			throw new BadRequestException(ErrorString.QUANTITY_NOT_ENOUGH);
		Cart cart = user.getCart();
		Date date = Date.valueOf(LocalDate.now());
		List<CartDetail> cartDetails;
		if (cart == null) {
			cart = Cart.builder().user(user).dateCreate(date).build();
			cart = cartRepository.save(cart);
		}
		CartProductFormatId cartProductFormatID = CartProductFormatId.builder().cartId(cart.getId())
				.formatId(cartItemRequest.getFormatId()).productId(cartItemRequest.getProductId()).build();
		CartDetail cartDetail = CartDetail.builder().cart(cart).productFormat(productFormat)
				.firstPrice(productFormat.getPrice() * cartItemRequest.getQuantity())
				.finalPrice(productFormat.getPrice() * cartItemRequest.getQuantity())
				.quantity(cartItemRequest.getQuantity()).Id(cartProductFormatID).build();
		cartDetails = cart.getCartDetails();
		if (cartDetails != null) {
			boolean checkExist = false;
			for (CartDetail item : cartDetails) {
				if (item.getId().getFormatId() == cartItemRequest.getFormatId()
						&& item.getId().getProductId() == cartItemRequest.getProductId()) {
					item.setFinalPrice(item.getFinalPrice() + cartDetail.getFinalPrice());
					item.setFirstPrice(item.getFirstPrice() + cartDetail.getFirstPrice());
					item.setQuantity(item.getQuantity() + cartItemRequest.getQuantity());
					checkExist = true;
				}
			}
			if (checkExist == false)
				cartDetails.add(cartDetail);
		} else {
			cartDetails = new ArrayList<CartDetail>();
			cartDetails.add(cartDetail);
		}
		productFormat.setQuantity(productFormat.getQuantity() - cartItemRequest.getQuantity());
		cart.setCartDetails(cartDetails);
		cart.setFirstPrice(cart.getFirstPrice() + cartDetail.getFirstPrice());
		cart.setFinalPrice(cart.getFinalPrice() + cartDetail.getFinalPrice());
		cart.setQuantity(cart.getQuantity() + cartDetail.getQuantity());
		cartRepository.save(cart);
		productFormatRepository.save(productFormat);
		return SuccessString.CART_ITEM_ADD_SUCCESS;
	}

	@Override
	public void updateCartItem(CartItemIdRequest cartItemRequest) {
		Cart cart = cartRepository.findById(cartItemRequest.getCartId())
				.orElseThrow(() -> new NotFoundException(ErrorString.CART_NOT_FOUND));
		ProductFormatID formatID = ProductFormatID.builder().formatID(cartItemRequest.getFormatId())
				.productID(cartItemRequest.getProductId()).build();
		ProductFormat productFormat = productFormatRepository.findById(formatID)
				.orElseThrow(() -> new NotFoundException(ErrorString.PRODUCT_TYPE_NOT_FOUND));
		if (productFormat.getQuantity() < cartItemRequest.getQuantity())
			throw new BadRequestException(ErrorString.QUANTITY_NOT_ENOUGH);
		if (cartItemRequest.getQuantity() <= 0)
			throw new BadRequestException(ErrorString.QUANTITY_NOT_ENOUGH);
		List<CartDetail> cartDetails = cart.getCartDetails();
		for (CartDetail detail : cartDetails) {
			if (detail.getId().getCartId() == cartItemRequest.getCartId()
					&& detail.getId().getFormatId() == cartItemRequest.getFormatId()
					&& detail.getId().getProductId() == cartItemRequest.getProductId()) {
				cart.setQuantity(cart.getQuantity() - detail.getQuantity() + cartItemRequest.getQuantity());
				cart.setFinalPrice(cart.getFinalPrice() - detail.getFinalPrice()
						+ cartItemRequest.getQuantity() * productFormat.getPrice());
				cart.setFirstPrice(cart.getFirstPrice() - detail.getFirstPrice()
						+ cartItemRequest.getQuantity() * productFormat.getPrice());
				detail.setQuantity(cartItemRequest.getQuantity());
				detail.setFinalPrice(cartItemRequest.getQuantity() * productFormat.getPrice());
				detail.setFirstPrice(cartItemRequest.getQuantity() * productFormat.getPrice());
			}
		}
		cart.setCartDetails(cartDetails);
		cartRepository.save(cart);
	}

	@Override
	public Integer getCountItemCart(String email) {
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
		Cart cart = user.getCart();
		if (cart == null)
			throw new BadRequestException(ErrorString.CART_EMPTY);
		if (cart.getCartDetails() == null)
			throw new BadRequestException(ErrorString.CART_EMPTY);
		Integer count = cartRepository.findCountItemByCartId(cart.getId());
		if (count == null)
			return 0;
		return count;
	}

}
