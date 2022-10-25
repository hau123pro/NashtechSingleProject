package service.order;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dto.reponse.HeaderResponse;
import dto.reponse.OrderItemRespone;
import dto.reponse.OrderRespone;
import entity.OrderDetail;
import entity.Orders;
import entity.User;
import entity.ManytoManyID.OrderItemID;
import exception.BadRequestException;
import mappers.OrderMapper;
import repository.orders.IOrderDetailRepository;
import repository.orders.IOrderRepository;
import repository.user.IUserRepository;
import service.cart.ICartService;
import utils.constant.ErrorString;
import utils.constant.Status;
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
		// TODO Auto-generated method stub
		Optional<Orders> optional=iOrderRepository.findById(orderId);
		Orders orders=optional.orElseThrow(()->new BadRequestException(ErrorString.ORDER_NOT_FOUND));
		OrderRespone orderRespone=orderMapper.getOrderById(orders);
		return orderRespone;
	}

	@Override
	public HeaderResponse<OrderRespone> getAllOrder(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Orders> page=iOrderRepository.findAll(pageable);
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
		
		orders.setUser(user);
		orders.setStatus(Status.ACTIVE.getValue());
		orders=iOrderRepository.save(orders);
		List<OrderDetail> details=orderMapper.convertCartItemToOrder(user);
		Set<OrderDetail> set=new HashSet<>(details);
		for(OrderDetail detail:set) {
			detail.setOrder(orders);
			OrderItemID id=new OrderItemID(detail.getOrder().getID(),detail.getProductFormat().getProduct().getId(),	
										detail.getProductFormat().getFormat().getID());
			detail.setId(id);
		}
		orders.setOrderDetails(set);
		iOrderRepository.save(orders);
		iCartService.deleteAllCartItem(user.getCart());
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
		Optional<Orders> optional=iOrderRepository.findById(orderId);
		Orders orders=optional.orElseThrow(()->new BadRequestException(ErrorString.ORDER_NOT_FOUND));
		List<OrderItemRespone> itemRespones=orderMapper.getItemOderById(orders);
		return itemRespones;
	}

}
