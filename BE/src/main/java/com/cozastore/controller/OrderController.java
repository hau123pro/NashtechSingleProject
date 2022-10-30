package com.cozastore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.OrderRespone;
import com.cozastore.entity.Orders;
import com.cozastore.mappers.OrderMapper;
import com.cozastore.service.order.IOrderService;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
	
	@Autowired 
	private IOrderService iOrderService;
	
	@GetMapping("/admin")
	public ResponseEntity<List<OrderRespone>> getAllOrder(@PageableDefault(size=5) Pageable pageable){
		HeaderResponse<OrderRespone> headerResponse=iOrderService.getAllOrder(pageable);
		return ResponseEntity.ok().headers(headerResponse.getHeaders()).body(headerResponse.getItems());
	}
	

	
}	
