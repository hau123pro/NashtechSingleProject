package com.cozastore.service.user;

import java.sql.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cozastore.entity.Cart;
import com.cozastore.entity.Orders;
import com.cozastore.entity.User;
import com.cozastore.exception.BadRequestException;
import com.cozastore.exception.NotFoundException;
import com.cozastore.repository.user.IUserRepository;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.Role;
import com.cozastore.utils.constant.Status;

@Service
public class UserService implements IUserService{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private IUserRepository userRepository;

	

	@Override
	public User getUserByEmail(String email) {
		Optional<User> optional=userRepository.findUserByEmail(email);
		User user=optional.orElseThrow(()->new NotFoundException(ErrorString.USER_NOT_FOUND));
		return user;
	}

	@Override
	public String changePassword(String email, String password, String password2) {

		if (password != null && !password.equals(password2)) {
			throw new BadRequestException(ErrorString.PASS_NOT_MATCH);
		}
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
		user.setPassword(password2);
		userRepository.save(user);
		return "Password successfully changed!";
	}
	
	@Override
	public String registerUser(User user, String password2) {
		if (user.getPassword() != null && !user.getPassword().equals(password2)) {
			throw new BadRequestException(ErrorString.PASS_NOT_MATCH);
		}
		if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
			throw new BadRequestException(ErrorString.EMAIL_IN_USE);
		}
		user.setStatus(Status.ACTIVE.getValue());
		user.setRoles(Role.USER.getValue());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		long millis = System.currentTimeMillis();
		// creating a new object of the class Date
		Date date = new Date(millis);
		user.setDateCreate(date);
		user.setDateOfBirth(date);
		user.setPhone("");
		userRepository.save(user);
		return "User successfully registered.";
	}
	
	
	public Cart getCartByUser(String email) {
		Optional<User> optional=userRepository.findUserByEmail(email);
		User user=optional.orElseThrow(()->new NotFoundException(ErrorString.USER_NOT_FOUND));
		if(user.getCart()==null) {
			throw new BadRequestException(ErrorString.ITEM_CART_NOT_FOUND);
		}
		return user.getCart();
	}
	@Override
	public Set<Orders> getOrdersByUser(String email) {
		Optional<User> optional=userRepository.findUserByEmail(email);
		User user=optional.orElseThrow(()->new NotFoundException(ErrorString.USER_NOT_FOUND));
		
		return user.getOrders();
	}
	
	

	
}
