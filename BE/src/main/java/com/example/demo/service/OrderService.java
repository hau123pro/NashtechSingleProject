package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.DTO.reponse.HeaderResponse;
import com.example.demo.DTO.reponse.OrderItemRespone;
import com.example.demo.DTO.reponse.OrderRespone;
import com.example.demo.entity.Orders;

public interface OrderService {
	public OrderRespone getOrderById(Integer orderId);
	
	public HeaderResponse<OrderRespone> getAllOrder(Pageable pageable);
	
	public String addOrder(String email);
	
	public List<OrderRespone> getOrderByUser(String email);
	
	public List<OrderItemRespone> getAllItemOrderById(Integer orderId);
	
}
