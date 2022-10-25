package repository.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import entity.Product;

public interface IProductRepository extends JpaRepository<Product, Integer>{
	public Page<Product> findByStatus(Pageable pageable,int status);
}
