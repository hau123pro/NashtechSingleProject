package com.cozastore.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozastore.entity.Cart;
import com.cozastore.entity.ManytoManyID.CartProductFormatID;

public interface ICartRepository extends JpaRepository<Cart, Integer>{
	
}
