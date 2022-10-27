package com.cozastore.service.order;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.OrderItemRespone;
import com.cozastore.dto.reponse.OrderRespone;
import com.cozastore.entity.OrderDetail;
import com.cozastore.entity.Orders;
import com.cozastore.entity.User;
import com.cozastore.entity.ManytoManyID.OrderItemID;
import com.cozastore.exception.BadRequestException;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.OrderMapper;
import com.cozastore.repository.orders.IOrderDetailRepository;
import com.cozastore.repository.orders.IOrderRepository;
import com.cozastore.repository.user.IUserRepository;
import com.cozastore.service.cart.ICartService;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.Status;
@Service
public class OrderService implements IOrderService{
	
	@Autowired
	private IOrderRepository iOrderRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired 
	private OrderMapper orderMapper;
	
	@Autowired
	private IOrderDetailRepository iOrderDetailRepository;	
	
	@Autowired 
	private ICartService iCartService;

	
	@Override
	public OrderRespone getOrderById(Integer orderId) {
		Optional<Orders> optional=iOrderRepository.findById(orderId);
		Orders orders=optional.orElseThrow(()->new NotFoundException(ErrorString.ORDER_NOT_FOUND));
		OrderRespone orderRespone=orderMapper.getOrderById(orders);
		return orderRespone;
	}

	@Override
	public HeaderResponse<OrderRespone> getAllOrder(Pageable pageable) {
		Page<Orders> page=iOrderRepository.findAll(pageable);
		HeaderResponse<OrderRespone> headerResponse=orderMapper.getAllOrder(page);
		return headerResponse;
	}

	@Override
	@Transactional
	public String addOrder(String email) {
		User user = userRepository.findUserByEmail(email)
									.orElseThrow(()-> new NotFoundException(ErrorString.USER_NOT_FOUND));
		Orders orders=orderMapper.convertCartToOrders(user.getCart(),user);

		orders.setUser(user);
		orders.setStatus(Status.ACTIVE.getValue());
		orders=iOrderRepository.save(orders);
		List<OrderDetail> details=orderMapper.convertCartItemToOrder(user);
		Set<OrderDetail> set=new HashSet<>(details);
		for(OrderDetail detail:set) {
			detail.setOrder(orders);
			OrderItemID id=new OrderItemID(detail.getOrder().getID(),detail.getProductFormat().getProduct().getId(),	
										detail.getProductFormat().getFormat().getId());
			detail.setId(id);
		}
		orders.setOrderDetails(set);
		iOrderRepository.save(orders);
		iCartService.deleteAllCartItem(user.getCart());
		return "Order sucessfully add";
	}
	

	@Override
	public List<OrderRespone> getOrderByUser(String email) {
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(()-> new NotFoundException(ErrorString.USER_NOT_FOUND));
		List<OrderRespone> list=orderMapper.getOrderByUser(user.getOrders());
		return list;
	}

	@Override
	public List<OrderItemRespone> getAllItemOrderById(Integer orderId) {
		Optional<Orders> optional=iOrderRepository.findById(orderId);
		Orders orders=optional.orElseThrow(()->new NotFoundException(ErrorString.ORDER_NOT_FOUND));
		List<OrderItemRespone> itemRespones=orderMapper.getItemOderById(orders);
		return itemRespones;
	}

}
