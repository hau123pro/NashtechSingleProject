package com.example.demo.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.example.demo.DTO.reponse.CartItemRespone;
import com.example.demo.DTO.reponse.CartRespone;
import com.example.demo.DTO.reponse.HeaderResponse;
import com.example.demo.DTO.reponse.OrderItemRespone;
import com.example.demo.DTO.reponse.OrderRespone;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartDetail;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Orders;
import com.example.demo.entity.User;
import com.example.demo.entity.ManytoManyID.OrderItemID;
import com.example.demo.exception.BadRequestException;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class OrderMapper {
	@Autowired
	private UtilMapper utilMapper;
	
	
	
	
	
	public List<OrderRespone> getOrderByUser(Set<Orders> set) {
		List<Orders> list=new ArrayList<Orders>(set);
		return utilMapper.convertToResponseList(list, OrderRespone.class);
		
	}
	
	public OrderRespone getOrderById(Orders order) {
		return utilMapper.convertToEntity(order, OrderRespone.class);
	}
	public List<OrderItemRespone> getItemOderById(Orders order) {
		return order.getOrderDetails().stream()
			.map(item->converOrderItemToRepone(item))
			.collect(Collectors.toList());
		
	}
	public OrderItemRespone converOrderItemToRepone(OrderDetail orderDetail) {
		return OrderItemRespone.builder()
								.fisrtPrice(orderDetail.getPrice())
								.FormatBonusPrice(orderDetail.getProductFormat().getFormat().getBonusPrice())
								.formatName(orderDetail.getProductFormat().getFormat().getFormatName())
								.imgUrl(orderDetail.getProductFormat().getProduct().getImgUrl())
								.productName(orderDetail.getProductFormat().getProduct().getProductName())
								.quantity(orderDetail.getQuantity()).build();
		
	}
	public HeaderResponse<OrderRespone> getAllOrder(Page<Orders> page) {
		return utilMapper.getHeaderResponse(page.getContent(), page.getTotalPages(), page.getTotalElements(), OrderRespone.class);
	} 
	public Orders convertCartToOrders(Cart cart,User user) {
		if(cart==null) throw new BadRequestException("Cart is empty");
		if(cart.getCartDetails().size()==0) throw new BadRequestException("Cart is blank");
		CartRespone cartRespone=utilMapper.convertToEntity(cart, CartRespone.class);
		Orders orders=utilMapper.convertToEntity(cartRespone, Orders.class);
		
		return orders;
	}
	public List<OrderDetail> convertCartItemToOrder(User user){
		List<CartDetail> cartDetails=new ArrayList<>(user.getCart().getCartDetails());
		List<OrderDetail> details=utilMapper.convertToResponseList(cartDetails,OrderDetail.class);
		return details;
	}
}
