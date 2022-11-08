package com.cozastore.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.AuthorPageResponse;
import com.cozastore.dto.reponse.AuthorResponse;
import com.cozastore.dto.reponse.CartRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.OrderItemRespone;
import com.cozastore.dto.reponse.OrderRespone;
import com.cozastore.dto.reponse.ReviewRespone;
import com.cozastore.dto.reponse.UserInformationRespone;
import com.cozastore.dto.request.CartItemIdRequest;
import com.cozastore.dto.request.CartItemRequest;
import com.cozastore.dto.request.ReviewRequest;
import com.cozastore.dto.request.UserInfoRequest;
import com.cozastore.dto.request.UserStatusRequest;
import com.cozastore.mappers.UserMapper;
import com.cozastore.service.author.IAuthorService;
import com.cozastore.service.cart.ICartService;
import com.cozastore.service.order.IOrderService;
import com.cozastore.service.review.IReviewService;
import com.cozastore.service.user.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/client")
//@PreAuthorize("hasAuthority('ROLE_USER')")
public class UserController {

	@Autowired
	IUserService userService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	IAuthorService authorService;

	@Autowired
	private ICartService cartService;

	@GetMapping("/info")
	public ResponseEntity<UserInformationRespone> getInfor(Principal principal) {
		return ResponseEntity.ok(userService.getUserByEmail(principal.getName()));
	}

	@PutMapping(value = "/info/update")
	public ResponseEntity<Object> updateInfoUser(@Valid @RequestBody UserInfoRequest infoRequest, Principal principal) {
		userService.changeInfoUser(infoRequest, principal.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/orders")
	public ResponseEntity<List<OrderRespone>> getOrdersUser(Principal principal) {
		return ResponseEntity.ok(orderService.getOrderByUser(principal.getName()));
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderRespone> getOrdersUserById(@PathVariable Integer orderId) {
		return ResponseEntity.ok(orderService.getOrderById(orderId));
	}

	@PostMapping("/add")
	public String addNewOrder(Principal principal) {
		return orderService.addOrder(principal.getName());
	}

	@GetMapping("/order/{orderId}/item")
	public ResponseEntity<List<OrderItemRespone>> getOrderItemByOrderId(@PathVariable Integer orderId) {
		return ResponseEntity.ok(orderService.getAllItemOrderById(orderId));
	}

	@PostMapping("/cart/delete/item")
	public ResponseEntity<String> removeItemInCart(@RequestBody CartItemIdRequest cartItem) {
//		return ResponseEntity.ok(""+cartItem.getProductID());
		return ResponseEntity.ok(cartService.deleteCartItemById(cartItem));
	}

	@PostMapping("/order/add")
	public void addOrder(Principal principal) {
		orderService.addOrder(principal.getName());
	}

	@PostMapping("/cart/add")
	public ResponseEntity<String> addProductToCart(@Valid @RequestBody CartItemRequest cartItemRequest,
			Principal principal) {
		return ResponseEntity.ok(cartService.addProductToCart(cartItemRequest, principal.getName()));
	}

	@PutMapping("/cart/update")
	public void updateProductToCart(@Valid @RequestBody CartItemIdRequest cartItemRequest) {
		cartService.updateCartItem(cartItemRequest);
	}

}
