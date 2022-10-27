package com.cozastore.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozastore.entity.CartDetail;
import com.cozastore.entity.ManytoManyID.CartProductFormatID;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, CartProductFormatID>{

}
