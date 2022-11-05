package com.cozastore.controller;

import java.io.IOException;
import java.util.List;

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

import com.cozastore.dto.reponse.ProductPageInfoResponse;
import com.cozastore.dto.reponse.ProductRespone;
import com.cozastore.dto.request.FilterRequest;
import com.cozastore.dto.request.ProductInfoRequest;
import com.cozastore.dto.request.ProductRequest;
import com.cozastore.dto.request.ProductStatusRequest;
import com.cozastore.service.cloudinary.ICloudinaryService;
import com.cozastore.service.product.ProductService;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping(value = "/shop")
	public ResponseEntity<ProductPageInfoResponse> getProductActive(@PageableDefault(size = 8) Pageable page) {
		return ResponseEntity.ok(productService.getAllProductActive(page));
	}

	@GetMapping(value = "/feature")
	public ResponseEntity<ProductPageInfoResponse> getProductFeatureActive() {
		return ResponseEntity.ok(productService.getProductFeature());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductRespone> getProductById(@PathVariable Integer id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}

	@GetMapping("/filter")
	public ResponseEntity<ProductPageInfoResponse> getProductFilter(@PageableDefault(size = 2) Pageable page,
			FilterRequest filter) {
		System.out.println(filter.getAuthorId());
		return ResponseEntity.ok(productService.getProductFilter(page, filter));
	}
}
