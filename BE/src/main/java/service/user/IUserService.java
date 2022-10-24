package service.user;

import java.util.Set;

import entity.Cart;
import entity.Orders;
import entity.User;

public interface IUserService {
	public User getUserByEmail(String email);

	public Cart getCartByUser(String email);

	public Set<Orders> getOrdersByUser(String email);

	public String registerUser(User user, String password2);

	public String changePassword(String email, String password, String password2);
}
