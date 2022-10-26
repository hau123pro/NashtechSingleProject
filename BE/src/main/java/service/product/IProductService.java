package service.product;

import java.util.List;

import org.springframework.data.domain.Pageable;

import dto.reponse.ProductRespone;
import dto.request.FilterRequest;
import dto.request.ProductInfoRequest;
import dto.request.ProductStatusRequest;
import entity.Product;

public interface IProductService {
	public List<ProductRespone> getAllProduct(Pageable pageable);
	
	public ProductRespone getProductById(Integer id);
	
	public String updateInfoProduct(ProductInfoRequest infoRequest);

	public List<ProductRespone> getAllProductActive(Pageable pageable);

	public String insertProduct(ProductInfoRequest infoRequest);

	public String updateStatusProduct(ProductStatusRequest productStatusRequest);

	List<ProductRespone> getProductFilter(Pageable pageable, FilterRequest filter);
}
