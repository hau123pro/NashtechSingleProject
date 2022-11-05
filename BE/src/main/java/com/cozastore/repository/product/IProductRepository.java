package com.cozastore.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cozastore.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Integer>{
	public Page<Product> findByStatus(Pageable pageable,int status);
	
	@Query("SELECT DISTINCT p "
			+ "FROM Product p , ProductCategory pc , ProductFormat pf "
			+ "WHERE p.id=pc.product.id and p.id=pf.product.id "
			+ "and ( pc.category.id=:category or :category is null ) "
			+ "and ( p.author.id=:author or :author is null ) "
			+ "and ( pf.format.id=:format or :format is null ) "
			+ "and ( pf.price >= :first or :first is null ) "
			+ "and ( pf.price <= :final or :final is null ) ")
	public Page<Product> findProductsByFilterParams(
			@Param("category") Integer categoryid,
			@Param("author") Integer authorid,
			@Param("format") Integer formatid,
			@Param("first") Double fisrtPrice,
			@Param("final") Double finalPrice,
			Pageable pageable);
	
	@Query("SELECT MAX(pf.price)"
		  + "FROM Product p, ProductFormat pf "
		  + "WHERE  p.id=pf.product.id "
		  + "and p.id=:product")
	public Double findProductMaxPriceById(@Param("product") Integer productid);
	
	@Query("SELECT MIN(pf.price)"
			  + "FROM Product p, ProductFormat pf "
			  + "WHERE  p.id=pf.product.id "
			  + "and p.id=:product")
		public Double findProductMinPriceById(@Param("product") Integer productid);
	
	public Page<Product> findByStatus(Integer status,Pageable pageable);

	
}
