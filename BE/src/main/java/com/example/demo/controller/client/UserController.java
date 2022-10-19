package com.example.demo.controller.client;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.reponse.CartItemRespone;
import com.example.demo.DTO.reponse.OrderRespone;
import com.example.demo.DTO.reponse.UserInformationRespone;
import com.example.demo.entity.Cart;
import com.example.demo.mappers.OrderMapper;
import com.example.demo.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client/users")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@GetMapping("/info")
	public ResponseEntity<UserInformationRespone> getInfor(Principal principal) {
		return ResponseEntity.ok(userMapper.getUserInfo(principal.getName()));
	}
	
	@GetMapping("/cart")
	public ResponseEntity<List<CartItemRespone>> getCart(Principal principal) {
		return ResponseEntity.ok(userMapper.getCart(principal.getName()));
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<OrderRespone>> getOrders(Principal principal) {
		return ResponseEntity.ok(orderMapper.getOrder(principal.getName()));
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderRespone> getOrders(@PathVariable Integer orderId) {
		return ResponseEntity.ok(orderMapper.getOrderById(orderId));
	}
	@GetMapping("/order/{orderId}/item")
	public ResponseEntity<OrderRespone> getOrders(@PathVariable Integer orderId) {
		return ResponseEntity.ok(orderMapper.getOrderById(orderId));
	}
}
