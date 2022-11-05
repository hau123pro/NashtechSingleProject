package com.cozastore.controller.admin;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.ProductPageInfoResponse;
import com.cozastore.dto.request.ProductInfoRequest;
import com.cozastore.dto.request.ProductRequest;
import com.cozastore.dto.request.ProductStatusRequest;
import com.cozastore.service.cloudinary.ICloudinaryService;
import com.cozastore.service.product.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/product")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class ProductAdminController {

	@Autowired
	ProductService productService;

	@Autowired
	ICloudinaryService cloudinaryService;

	@GetMapping
	public ResponseEntity<ProductPageInfoResponse> getProduct(@PageableDefault(size = 8) Pageable page) {
		return ResponseEntity.ok(productService.getAllProduct(page));
	}

	@PutMapping(value = "/update", consumes = "multipart/form-data")
	public ResponseEntity<String> updateProductById(@Valid @ModelAttribute ProductInfoRequest infoRequest)
			throws IOException {
		return ResponseEntity.ok(productService.updateInfoProduct(infoRequest));
	}

	@PostMapping(value = "/insert", consumes = "multipart/form-data")
	public ResponseEntity<String> insertProduct(@Valid @ModelAttribute ProductRequest infoRequest) throws IOException {
		return ResponseEntity.ok(productService.insertProduct(infoRequest));
	}

	@PutMapping(value = "/status/update")
	public ResponseEntity<String> updateStatusProduct(@Valid @RequestBody ProductStatusRequest request) {
		return ResponseEntity.ok(productService.updateStatusProduct(request));
	}
}
