package com.example.demo.controller.admin;

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

import com.example.demo.DTO.reponse.HeaderResponse;
import com.example.demo.DTO.reponse.OrderRespone;
import com.example.demo.entity.Orders;
import com.example.demo.mappers.OrderMapper;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/api/v1/admin/order")
public class OrderController {
	
	@Autowired 
	private OrderService orderService;
	
	@GetMapping()
	public ResponseEntity<List<OrderRespone>> getAllOrder(@PageableDefault(size=5) Pageable pageable){
		HeaderResponse<OrderRespone> headerResponse=orderService.getAllOrder(pageable);
		return ResponseEntity.ok().headers(headerResponse.getHeaders()).body(headerResponse.getItems());
	}
	@PostMapping("/add")
	public String  addNewOrder(Principal principal) {
		return orderService.addOrder(principal.getName());
	}
//	@PutMapping("/update")
//	public 
	
}	
