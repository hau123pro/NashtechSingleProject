package repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Product;

public interface IProductRepository extends JpaRepository<Product, Integer>{

}
