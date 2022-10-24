package repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Orders;

public interface IOrderRepository extends JpaRepository<Orders, Integer>{
	
}
