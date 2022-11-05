package com.cozastore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.OrderPageResponse;
import com.cozastore.dto.reponse.OrderRespone;
import com.cozastore.service.order.IOrderService;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@PostMapping("/add")
	public void addOrder(Principal principal) {
		orderService.addOrder(principal.getName());
	}
	
	@GetMapping("/")
	public ResponseEntity<OrderPageResponse> getAllOrderByPage(Pageable pageable){
		return ResponseEntity.ok(orderService.getAllOrderByPage(pageable));
	}
	
	
}
