package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Orders;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Orders getOrderById(Integer orderId) {
		// TODO Auto-generated method stub
		Optional<Orders> optional=orderRepository.findById(orderId);
		Orders orders=optional.orElseThrow(()->new BadRequestException("order not found"));
		return orders;
	}

}
