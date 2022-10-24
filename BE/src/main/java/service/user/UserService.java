package service.user;

import java.sql.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import entity.Cart;
import entity.Orders;
import entity.User;
import exception.BadRequestException;
import repository.user.IUserRepository;
import utils.constant.ErrorString;
import utils.constant.Role;

@Service
public class UserService implements IUserService{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private IUserRepository userRepository;

	
	// get/insert/updatePassword

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<User> optional=userRepository.findUserByEmail(email);
		User user=optional.orElseThrow(()->new BadRequestException("User not found"));
		return user;
	}

	@Override
	public String changePassword(String email, String password, String password2) {

		if (password != null && !password.equals(password2)) {
			throw new BadRequestException(ErrorString.PASS_NOT_MATCH);
		}
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(ErrorString.USER_NOT_FOUND));
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
		user.setStatus("Active");
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
		User user=optional.orElseThrow(()->new BadRequestException("User not found"));
		if(user.getCart()==null) {
			throw new BadRequestException("Not any item in Cart");
		}
		return user.getCart();
	}
	@Override
	public Set<Orders> getOrdersByUser(String email) {
		// TODO Auto-generated method stub
		Optional<User> optional=userRepository.findUserByEmail(email);
		User user=optional.orElseThrow(()->new BadRequestException("User not found"));
		
		return user.getOrders();
	}
	
	

	
}
