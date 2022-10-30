package com.cozastore.repository.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cozastore.entity.Format;
import com.cozastore.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Integer>{
	public Page<Product> findByStatus(Pageable pageable,int status);
	
	@Query("SELECT DISTINCT p "
			+ "FROM Product p , ProductCategory pc , ProductFormat pf "
			+ "WHERE p.Id=pc.product.Id and p.Id=pf.product.Id "
			+ "and ( pc.category.id=:category or :category is null ) "
			+ "and ( p.author.id=:author or :author is null ) "
			+ "and ( pf.format.id=:format or :format is null ) "
			+ "and ( pf.price>=:first or :first is null ) "
			+ "and ( pf.price<=:final or :final is null ) ")
	public Page<Product> findProductsByFilterParams(
			@Param("category") Integer categoryid,
			@Param("author") Integer authorid,
			@Param("format") Integer formatid,
			@Param("first") Integer fisrtPrice,
			@Param("final") Integer finalPrice,
			Pageable pageable);
	
	public Page<Product> findByStatus(Integer status,Pageable pageable);

	
}
