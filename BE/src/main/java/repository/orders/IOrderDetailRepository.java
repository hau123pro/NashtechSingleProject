package repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.OrderDetail;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Integer>{

}
