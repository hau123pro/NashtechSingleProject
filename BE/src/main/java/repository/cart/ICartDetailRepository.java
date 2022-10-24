package repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.CartDetail;
import entity.ManytoManyID.CartProductFormatID;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, CartProductFormatID>{

}
