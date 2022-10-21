package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@RestController
@RequestMapping("/api/v1/admin/product")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	@GetMapping
	public List<Product> getProduct() {
		return productRepository.findAll();
	}
}
