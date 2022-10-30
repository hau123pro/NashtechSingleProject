package com.cozastore.controller;

import java.io.IOException;
import java.util.List;

import javax.swing.plaf.multi.MultiPanelUI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cozastore.dto.reponse.ProductRespone;
import com.cozastore.dto.request.FilterRequest;
import com.cozastore.dto.request.ProductInfoRequest;
import com.cozastore.dto.request.ProductRequest;
import com.cozastore.dto.request.ProductStatusRequest;
import com.cozastore.entity.Product;
import com.cozastore.repository.product.IProductRepository;
import com.cozastore.service.cloudinary.ICloudinaryService;
import com.cozastore.service.product.ProductService;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ICloudinaryService cloudinaryService;
	
	@GetMapping("/admin")
	public ResponseEntity<List<ProductRespone>> getProduct(@PageableDefault(size=8) Pageable page) {
		return ResponseEntity.ok(productService.getAllProduct(page));
	}
	
	@GetMapping(value="/shop")
	public ResponseEntity<List<ProductRespone>> getProductActive(@PageableDefault(size=8) Pageable page) {
		return ResponseEntity.ok(productService.getAllProduct(page));
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ProductRespone> getProductById(@PathVariable Integer id){
		return ResponseEntity.ok(productService.getProductById(id));
	}
	@PutMapping(value="/admin/update",consumes= "multipart/form-data")
	public ResponseEntity<String> updateProductById(@Valid @ModelAttribute ProductInfoRequest infoRequest) throws IOException{
		return ResponseEntity.ok(productService.updateInfoProduct(infoRequest));
	}
	@PostMapping(value="/admin/insert",consumes="multipart/form-data")
	public ResponseEntity<String> insertProduct(@Valid @ModelAttribute ProductRequest infoRequest) throws IOException{
		return ResponseEntity.ok(productService.insertProduct(infoRequest));
	}
	@PutMapping(value="/admin/status/update")
	public ResponseEntity<String> updateStatusProduct(@Valid @RequestBody ProductStatusRequest request){
		return ResponseEntity.ok(productService.updateStatusProduct(request));
	}
	@GetMapping("/filter")
	public ResponseEntity<List<ProductRespone>> getProductFilter(@PageableDefault(size=2) Pageable page,@RequestBody FilterRequest filter ) {
		return ResponseEntity.ok(productService.getProductFilter(page, filter));
	}
}
