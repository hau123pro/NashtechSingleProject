package com.example.demo.controller.client;

import java.security.Principal;
import java.util.List;

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

import com.example.demo.DTO.reponse.CartItemRespone;
import com.example.demo.DTO.reponse.OrderItemRespone;
import com.example.demo.DTO.reponse.OrderRespone;
import com.example.demo.DTO.reponse.ProductRespone;
import com.example.demo.DTO.reponse.ReviewRespone;
import com.example.demo.DTO.reponse.UserInformationRespone;
import com.example.demo.DTO.request.CartItemRequest;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Review;
import com.example.demo.mappers.OrderMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ReviewService reviewService;
	

		
	@GetMapping("/info")
	public ResponseEntity<UserInformationRespone> getInfor(Principal principal) {
		return ResponseEntity.ok(userMapper.getUserInfo(principal.getName()));
	}
	
	@GetMapping("/cart")
	public ResponseEntity<List<CartItemRespone>> getCart(Principal principal) {
		return ResponseEntity.ok(userMapper.getCart(principal.getName()));
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
	@GetMapping("/shop")
	private ResponseEntity<List<ProductRespone>> getAllProduct(){
		
	}
	
}
