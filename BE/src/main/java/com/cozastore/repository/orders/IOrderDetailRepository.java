package com.cozastore.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozastore.entity.OrderDetail;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Integer>{

}
