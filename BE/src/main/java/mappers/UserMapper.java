package mappers;

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

import dto.reponse.CartItemRespone;
import dto.reponse.CartRespone;
import dto.reponse.OrderRespone;
import dto.reponse.UserInformationRespone;
import dto.request.CartItemRequest;
import entity.Cart;
import entity.CartDetail;
import entity.OrderDetail;
import entity.Orders;
import entity.ManytoManyID.CartProductFormatID;
import exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import service.user.IUserService;

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
