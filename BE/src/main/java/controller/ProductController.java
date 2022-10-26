package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.reponse.ProductRespone;
import dto.request.FilterRequest;
import dto.request.ProductInfoRequest;
import dto.request.ProductStatusRequest;
import entity.Product;
import repository.product.IProductRepository;
import service.product.ProductService;

@RestController
@RequestMapping("/v1/admin/product")
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductRespone>> getProduct(@PageableDefault(size=2) Pageable page) {
		return ResponseEntity.ok(productService.getAllProduct(page));
	}
	
	@GetMapping(value="/shop")
	public ResponseEntity<List<ProductRespone>> getProductActive(@PageableDefault(size=2) Pageable page) {
		return ResponseEntity.ok(productService.getAllProduct(page));
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ProductRespone> getProductById(Integer id){
		return ResponseEntity.ok(productService.getProductById(id));
	}
	@PutMapping(value="/update")
	public ResponseEntity<String> updateProductById(@Valid @RequestBody ProductInfoRequest infoRequest){
		return ResponseEntity.ok(productService.updateInfoProduct(infoRequest));
	}
	@PostMapping(value="/insert")
	public ResponseEntity<String> insertProduct(@Valid @RequestBody ProductInfoRequest infoRequest){
		return ResponseEntity.ok(productService.insertProduct(infoRequest));
	}
	@PutMapping(value="/status/update")
	public ResponseEntity<String> updateStatusProduct(@Valid @RequestBody ProductStatusRequest request){
		return ResponseEntity.ok(productService.updateStatusProduct(request));
	}
	@GetMapping("/filter")
	public ResponseEntity<List<ProductRespone>> getProductFilter(@PageableDefault(size=2) Pageable page, @Valid @RequestBody FilterRequest filter ) {
		return ResponseEntity.ok(productService.getProductFilter(page, filter));
	}
}
