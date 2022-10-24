package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.reponse.ProductRespone;
import entity.Product;
import repository.product.IProductRepository;
import service.product.ProductService;

@RestController
@RequestMapping("/v1/admin/product")
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public List<ProductRespone> getProduct(@PageableDefault(size=2) Pageable page) {
		return productService.getAllProduct(page);
	}
}
