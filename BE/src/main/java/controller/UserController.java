package controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.reponse.CartItemRespone;
import dto.reponse.CartRespone;
import dto.reponse.OrderItemRespone;
import dto.reponse.OrderRespone;
import dto.reponse.ProductRespone;
import dto.reponse.ReviewRespone;
import dto.reponse.UserInformationRespone;
import dto.request.CartItemRequest;
import dto.request.ReviewRequest;
import entity.Cart;
import entity.Review;
import lombok.RequiredArgsConstructor;
import mappers.OrderMapper;
import mappers.UserMapper;
import service.cart.ICartService;
import service.order.IOrderService;
import service.review.IReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/client")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;

	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private IReviewService reviewService;
	

		
	@GetMapping("/info")
	public ResponseEntity<UserInformationRespone> getInfor(Principal principal) {
		return ResponseEntity.ok(userMapper.getUserInfo(principal.getName()));
	}
	
	@GetMapping("/cart")
	public ResponseEntity<CartRespone> getCart(Principal principal) {
		return ResponseEntity.ok(cartService.getCart(principal.getName()));
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<OrderRespone>> getOrdersUser(Principal principal) {
		return ResponseEntity.ok(orderService.getOrderByUser(principal.getName()));
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderRespone> getOrdersUserById(@PathVariable Integer orderId) {
		return ResponseEntity.ok(orderService.getOrderById(orderId));
	}
	@GetMapping("/order/{orderId}/item")
	public ResponseEntity<List<OrderItemRespone>> getOrderItemByOrderId(@PathVariable Integer orderId) {
		return ResponseEntity.ok(orderService.getAllItemOrderById(orderId));
	}
	@PostMapping("/cart/delete/item")
	public ResponseEntity<String> removeItemInCart(
			@RequestBody CartItemRequest cartItem){
//		return ResponseEntity.ok(""+cartItem.getProductID());
		return ResponseEntity.ok(cartService.deleteCartItemById(cartItem));
	}
	@GetMapping("/review")
	private ResponseEntity<List<ReviewRespone>> getAllReview(){
		return ResponseEntity.ok(reviewService.getAllReview());
	}
	
	@PostMapping("/cart/add")
	public ResponseEntity<String> addProductToCart(@Valid @RequestBody CartItemRequest cartItemRequest,Principal principal){
		return ResponseEntity.ok(cartService.addProductToCart(cartItemRequest, principal.getName()));
	}
	@PostMapping("/review/add")
	public ResponseEntity<String> addReviewProduct(@Valid @RequestBody ReviewRequest reviewRequest,Principal principal){
		return ResponseEntity.ok(reviewService.addReviewToProduct(reviewRequest, principal.getName()));
	}
}
