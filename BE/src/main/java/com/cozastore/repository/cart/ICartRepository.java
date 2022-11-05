package com.cozastore.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cozastore.entity.Cart;

public interface ICartRepository extends JpaRepository<Cart, Integer>{
	@Query("SELECT COUNT(cd)"
			  + "FROM Cart c, CartDetail cd "
			  + "WHERE  c.id=cd.cart.id "
			  + "and c.id=:cart ")
	public int findCountItemByCartId(@Param("cart") Integer cartid);
}
