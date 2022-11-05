package com.cozastore.repository.productformat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cozastore.entity.ProductFormat;
import com.cozastore.entity.ManytoManyID.ProductFormatID;
@Repository
public interface IProductFormatRepository extends JpaRepository<ProductFormat, ProductFormatID>{
	@Query("SELECT pf "
			  + "FROM ProductFormat pf "
			  + "WHERE  pf.product.id=:product ")
	public List<ProductFormat> findByProductId(@Param("product") Integer productid);

}
