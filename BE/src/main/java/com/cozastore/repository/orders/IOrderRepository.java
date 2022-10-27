package com.cozastore.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozastore.entity.Orders;

public interface IOrderRepository extends JpaRepository<Orders, Integer>{
	
}
