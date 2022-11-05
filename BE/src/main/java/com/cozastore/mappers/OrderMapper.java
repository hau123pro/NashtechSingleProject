package com.cozastore.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.cozastore.dto.reponse.CartRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.OrderItemRespone;
import com.cozastore.dto.reponse.OrderRespone;
import com.cozastore.entity.Cart;
import com.cozastore.entity.CartDetail;
import com.cozastore.entity.OrderDetail;
import com.cozastore.entity.Orders;
import com.cozastore.entity.User;
import com.cozastore.entity.ManytoManyID.OrderItemID;
import com.cozastore.exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderMapper {
	@Autowired
	private UtilMapper utilMapper;

	public List<OrderRespone> convertListOrderToResponse(List<Orders> list) {
		return utilMapper.convertToResponseList(list, OrderRespone.class);

	}

	public OrderRespone getOrderById(Orders order) {
		return utilMapper.convertToEntity(order, OrderRespone.class);
	}

	public List<OrderItemRespone> getItemOderById(Orders order) {
		return order.getOrderDetails().stream().map(item -> converOrderItemToRepone(item)).collect(Collectors.toList());

	}

	public OrderItemRespone converOrderItemToRepone(OrderDetail orderDetail) {
		return OrderItemRespone.builder().fisrtPrice(orderDetail.getFirstPrice())
				.finalPrice(orderDetail.getFinalPrice())
				.formatName(orderDetail.getProductFormat().getFormat().getFormatName())
				.imgUrl(orderDetail.getProductFormat().getProduct().getImgUrl())
				.productName(orderDetail.getProductFormat().getProduct().getProductName())
				.quantity(orderDetail.getQuantity()).build();

	}

	public OrderDetail convertCartDetailToOrderDetail(CartDetail cartDetail) {
		return OrderDetail.builder().finalPrice(cartDetail.getFinalPrice()).firstPrice(cartDetail.getFirstPrice())
				.productFormat(cartDetail.getProductFormat()).quantity(cartDetail.getQuantity()).build();
	}

	public Orders convertCartToOrders(Cart cart, User user) {
		if (cart == null)
			throw new BadRequestException("Cart is empty");
		if (cart.getCartDetails().size() == 0)
			throw new BadRequestException("Cart is blank");
		CartRespone cartRespone = utilMapper.convertToEntity(cart, CartRespone.class);
		Orders orders = utilMapper.convertToEntity(cartRespone, Orders.class);

		return orders;
	}

	public List<OrderDetail> convertCartItemToOrder(User user, Orders orders) {
		List<CartDetail> cartDetails = new ArrayList<>(user.getCart().getCartDetails());
		List<OrderDetail> details = new ArrayList<>();
		for (CartDetail cartDetail : cartDetails) {
			OrderItemID id = OrderItemID.builder().orderID(orders.getId())
					.productID(cartDetail.getProductFormat().getId().getProductID())
					.formatID(cartDetail.getProductFormat().getFormat().getId()).build();
			OrderDetail detail = convertCartDetailToOrderDetail(cartDetail);
			detail.setOrder(orders);
			detail.setId(id);
			details.add(detail);
		}
		return details;
	}
}
