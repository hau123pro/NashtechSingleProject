package com.cozastore.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.criteria.Order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cozastore.dto.reponse.CartItemRespone;
import com.cozastore.dto.reponse.CartRespone;
import com.cozastore.dto.reponse.OrderRespone;
import com.cozastore.dto.reponse.UserInformationRespone;
import com.cozastore.dto.request.CartItemRequest;
import com.cozastore.entity.Cart;
import com.cozastore.entity.CartDetail;
import com.cozastore.entity.OrderDetail;
import com.cozastore.entity.Orders;
import com.cozastore.entity.ManytoManyID.CartProductFormatID;
import com.cozastore.exception.BadRequestException;
import com.cozastore.service.user.IUserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

	@Autowired
	private UtilMapper utilMapper;
	
	@Autowired
	private IUserService userService;
	
	public UserInformationRespone getUserInfo(String email) {
		return utilMapper.convertToEntity(userService.getUserByEmail(email), UserInformationRespone.class);
	}
	
}
