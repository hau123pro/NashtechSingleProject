package service.product;

import java.util.List;

import org.springframework.data.domain.Pageable;

import dto.reponse.ProductRespone;
import entity.Product;

public interface IProductService {
	public List<ProductRespone> getAllProduct(Pageable pageable);
}
