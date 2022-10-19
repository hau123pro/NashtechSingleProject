package com.example.demo.mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.DTO.reponse.OrderRespone;
import com.example.demo.entity.Orders;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class OrderMapper {
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	public List<OrderRespone> getOrder(String email) {
		Set<Orders> list=userService.getOrdersByUser(email);
		return list.stream()
			.map(item->convertOrderToRespone(item,OrderRespone.class))
			.collect(Collectors.toList());
		
	}
	public OrderRespone convertOrderToRespone(Orders order,Class<OrderRespone> classes) {
		
		return mapper.map(order,classes);
	}
	public OrderRespone getOrderById(Integer id) {
		Orders order=orderService.getOrderById(id);
		return convertOrderToRespone(order,OrderRespone.class);
	}
}
