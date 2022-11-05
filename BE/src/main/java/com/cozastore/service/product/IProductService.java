package com.cozastore.service.product;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cozastore.dto.reponse.ProductPageInfoResponse;
import com.cozastore.dto.reponse.ProductRespone;
import com.cozastore.dto.request.FilterRequest;
import com.cozastore.dto.request.ProductInfoRequest;
import com.cozastore.dto.request.ProductRequest;
import com.cozastore.dto.request.ProductStatusRequest;

public interface IProductService {
	public ProductPageInfoResponse getAllProduct(Pageable pageable);

	public ProductPageInfoResponse getProductFeature();

	public ProductRespone getProductById(Integer id);

	public String updateInfoProduct(ProductInfoRequest infoRequest) throws IOException;

	public ProductPageInfoResponse getAllProductActive(Pageable pageable);

	public String insertProduct(ProductRequest infoRequest) throws IOException;

	public String updateStatusProduct(ProductStatusRequest productStatusRequest);

	public ProductPageInfoResponse getProductFilter(Pageable pageable, FilterRequest filter);
}
