package com.cozastore.repository.productformat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozastore.entity.ProductFormat;
import com.cozastore.entity.ManytoManyID.ProductFormatID;
@Repository
public interface IProductFormatRepository extends JpaRepository<ProductFormat, ProductFormatID>{
//	public List<ProductFormat> findByProductId(Integer productId);

}
