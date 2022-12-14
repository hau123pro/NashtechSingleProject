package com.cozastore.service.order;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.OrderItemRespone;
import com.cozastore.dto.reponse.OrderPageResponse;
import com.cozastore.dto.reponse.OrderRespone;
import com.cozastore.dto.reponse.PageResponse;
import com.cozastore.entity.OrderDetail;
import com.cozastore.entity.Orders;
import com.cozastore.entity.User;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.OrderMapper;
import com.cozastore.mappers.PageMapper;
import com.cozastore.repository.orders.IOrderDetailRepository;
import com.cozastore.repository.orders.IOrderRepository;
import com.cozastore.repository.user.IUserRepository;
import com.cozastore.service.cart.ICartService;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.Status;

@Service
public class OrderService implements IOrderService {

	private IOrderRepository iOrderRepository;

	private IUserRepository userRepository;

	private OrderMapper orderMapper;

	private IOrderDetailRepository iOrderDetailRepository;

	private ICartService iCartService;

	private PageMapper pageMapper;

	@Autowired
	public OrderService(IOrderRepository iOrderRepository, IUserRepository userRepository, OrderMapper orderMapper,
			IOrderDetailRepository iOrderDetailRepository, ICartService iCartService, PageMapper pageMapper) {
		super();
		this.iOrderRepository = iOrderRepository;
		this.userRepository = userRepository;
		this.orderMapper = orderMapper;
		this.iOrderDetailRepository = iOrderDetailRepository;
		this.iCartService = iCartService;
		this.pageMapper = pageMapper;
	}

	@Override
	public OrderRespone getOrderById(Integer orderId) {
		Optional<Orders> optional = iOrderRepository.findById(orderId);
		Orders orders = optional.orElseThrow(() -> new NotFoundException(ErrorString.ORDER_NOT_FOUND));
		OrderRespone orderRespone = orderMapper.getOrderById(orders);
		return orderRespone;
	}

	@Override
	public OrderPageResponse getAllOrderByPage(Pageable pageable) {
		Page<Orders> page = iOrderRepository.findAll(pageable);
		List<OrderRespone> orderRespones = orderMapper.convertListOrderToResponse(page.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(),
				pageable.getPageSize());
		return OrderPageResponse.builder().orderRespones(orderRespones).pageResponse(pageResponse).build();
	}

	@Override
	@Transactional
	public String addOrder(String email) {
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
		Orders orders = orderMapper.convertCartToOrders(user.getCart(), user);
		orders.setUser(user);
		orders.setStatus(Status.ACTIVE.getValue());
		orders = iOrderRepository.save(orders);
		List<OrderDetail> details = orderMapper.convertCartItemToOrder(user, orders);
		orders.setOrderDetails(details);
		iOrderRepository.save(orders);
		iCartService.deleteAllCartItem(user.getCart());
		return "Order sucessfully add";
	}

	@Override
	public List<OrderRespone> getOrderByUser(String email) {
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
		List<OrderRespone> list = orderMapper.convertListOrderToResponse(user.getOrders());
		return list;
	}

	@Override
	public List<OrderItemRespone> getAllItemOrderById(Integer orderId) {
		Optional<Orders> optional = iOrderRepository.findById(orderId);
		Orders orders = optional.orElseThrow(() -> new NotFoundException(ErrorString.ORDER_NOT_FOUND));
		List<OrderItemRespone> itemRespones = orderMapper.getItemOderById(orders);
		return itemRespones;
	}

}
