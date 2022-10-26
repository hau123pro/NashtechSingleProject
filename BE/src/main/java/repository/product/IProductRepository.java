package repository.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entity.Product;

public interface IProductRepository extends JpaRepository<Product, Integer>{
	public Page<Product> findByStatus(Pageable pageable,int status);
	
	@Query("SELECT DISTINCT p "
			+ "FROM Product p , ProductCategory pc"
			+ "WHERE p.Id=pc.product.Id "
			+ " and pc.category.Id=:categoryId")
	public Page<Product> findByCategoryId(
			@Param("categoryId") Integer categoryId,
			Pageable pageable);
}
