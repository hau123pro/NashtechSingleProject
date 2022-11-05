package com.cozastore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.CategoryPageResponse;
import com.cozastore.dto.reponse.CategoryRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.request.CategoryInsertRequest;
import com.cozastore.dto.request.CategoryRequest;
import com.cozastore.dto.request.CategoryStatusRequest;
import com.cozastore.service.category.ICategoryService;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping("/active")
	public ResponseEntity<CategoryPageResponse> getCategoryActiveByPage( @PageableDefault(size=8) Pageable pageable){
		return ResponseEntity.ok(categoryService.getCategoryActiveByPage(pageable));
	}
	@GetMapping("/active/all")
	public ResponseEntity<List<CategoryRespone>> getAllCategoryActive( ){
		List<CategoryRespone> categoryRespones=categoryService.getAllCategoryActive();
		return ResponseEntity.ok(categoryRespones);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryRespone> getCategoryById(@PathVariable Integer id){
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}
	
}
