package mappers;

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

import dto.reponse.CartItemRespone;
import dto.reponse.CartRespone;
import dto.reponse.HeaderResponse;
import dto.reponse.OrderItemRespone;
import dto.reponse.OrderRespone;
import entity.Cart;
import entity.CartDetail;
import entity.OrderDetail;
import entity.Orders;
import entity.User;
import entity.ManytoManyID.OrderItemID;
import exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import service.order.IOrderService;
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
