package com.cozastore.service.product;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cozastore.dto.reponse.ProductRespone;
import com.cozastore.dto.request.FilterRequest;
import com.cozastore.dto.request.ProductInfoRequest;
import com.cozastore.dto.request.ProductStatusRequest;
import com.cozastore.entity.Product;

public interface IProductService {
	public List<ProductRespone> getAllProduct(Pageable pageable);
	
	public ProductRespone getProductById(Integer id);
	
	public String updateInfoProduct(ProductInfoRequest infoRequest);

	public List<ProductRespone> getAllProductActive(Pageable pageable);

	public String insertProduct(ProductInfoRequest infoRequest);

	public String updateStatusProduct(ProductStatusRequest productStatusRequest);

	List<ProductRespone> getProductFilter(Pageable pageable, FilterRequest filter);
}
