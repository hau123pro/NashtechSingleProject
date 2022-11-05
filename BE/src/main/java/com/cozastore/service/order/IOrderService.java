package com.cozastore.service.order;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.OrderItemRespone;
import com.cozastore.dto.reponse.OrderPageResponse;
import com.cozastore.dto.reponse.OrderRespone;
import com.cozastore.entity.Orders;

public interface IOrderService {
	public OrderRespone getOrderById(Integer orderId);

	public OrderPageResponse getAllOrderByPage(Pageable pageable);

	public String addOrder(String email);

	public List<OrderRespone> getOrderByUser(String email);

	public List<OrderItemRespone> getAllItemOrderById(Integer orderId);

}
