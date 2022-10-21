package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.reponse.HeaderResponse;
import com.example.demo.DTO.reponse.OrderItemRespone;
import com.example.demo.DTO.reponse.OrderRespone;
import com.example.demo.constant.ErrorString;
import com.example.demo.entity.CartDetail;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Orders;
import com.example.demo.entity.User;
import com.example.demo.entity.ManytoManyID.OrderItemID;
import com.example.demo.exception.BadRequestException;
import com.example.demo.mappers.OrderMapper;
import com.example.demo.mappers.UtilMapper;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;	
	
	@Autowired 
	private CartService cartService;

	
	@Override
	public OrderRespone getOrderById(Integer orderId) {
		// TODO Auto-generated method stub
		Optional<Orders> optional=orderRepository.findById(orderId);
		Orders orders=optional.orElseThrow(()->new BadRequestException(ErrorString.ORDER_NOT_FOUND));
		OrderRespone orderRespone=orderMapper.getOrderById(orders);
		return orderRespone;
	}

	@Override
	public HeaderResponse<OrderRespone> getAllOrder(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Orders> page=orderRepository.findAll(pageable);
		HeaderResponse<OrderRespone> headerResponse=orderMapper.getAllOrder(page);
		return headerResponse;
	}

	@Override
	@Transactional
	public String addOrder(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findUserByEmail(email)
									.orElseThrow(()-> new BadRequestException(ErrorString.USER_NOT_FOUND));
		Orders orders=orderMapper.convertCartToOrders(user.getCart(),user);
//		List<OrderDetail> details=orderMapper.convertCartItemToOrder(user);
//		for(OrderDetail detail:details) {
//			
//		}
//		orders.setUser(user);
//		orders.setStatus("Active");
//		orders=orderRepository.save(orders);
//		List<OrderDetail> details=orderMapper.convertCartItemToOrder(user);
//		Set<OrderDetail> set=new HashSet<>(details);
//		for(OrderDetail detail:set) {
//			detail.setOrder(orders);
//			OrderItemID id=new OrderItemID(detail.getOrder().getID(),detail.getProductFormat().getProduct().getID(),	
//										detail.getProductFormat().getFormat().getID());
//			detail.setId(id);
//		}
//		orders.setOrderDetails(set);
//		orderRepository.save(orders);
		cartService.deleteCart(user.getCart());
		return "Order sucessfully add";
		
//		return orders;
	}

	@Override
	public List<OrderRespone> getOrderByUser(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(()-> new BadRequestException(ErrorString.USER_NOT_FOUND));
		List<OrderRespone> list=orderMapper.getOrderByUser(user.getOrders());
		return list;
	}

	@Override
	public List<OrderItemRespone> getAllItemOrderById(Integer orderId) {
		// TODO Auto-generated method stub
		Optional<Orders> optional=orderRepository.findById(orderId);
		Orders orders=optional.orElseThrow(()->new BadRequestException(ErrorString.ORDER_NOT_FOUND));
		List<OrderItemRespone> itemRespones=orderMapper.getItemOderById(orders);
		return itemRespones;
	}

}
