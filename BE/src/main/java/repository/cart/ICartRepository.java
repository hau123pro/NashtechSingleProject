package repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Cart;
import entity.ManytoManyID.CartProductFormatID;

public interface ICartRepository extends JpaRepository<Cart, Integer>{
	
}
