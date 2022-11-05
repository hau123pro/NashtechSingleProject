package com.cozastore.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.CategoryPageResponse;
import com.cozastore.dto.request.CategoryInsertRequest;
import com.cozastore.dto.request.CategoryRequest;
import com.cozastore.dto.request.CategoryStatusRequest;
import com.cozastore.service.category.ICategoryService;

@RestController
@RequestMapping("/v1/admin/category")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class CategoryAdminController {
	@Autowired
	ICategoryService categoryService;

	@GetMapping("/")
	public ResponseEntity<CategoryPageResponse> getCategoryByPage(@PageableDefault(size = 8) Pageable pageable) {
		return ResponseEntity.ok(categoryService.getCategoryByPage(pageable));
	}

	@PutMapping("/admin/update")
	public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
		return ResponseEntity.ok(categoryService.updateCategory(categoryRequest));
	}

	@PostMapping("/insert")
	public ResponseEntity<String> insertProduct(@Valid @RequestBody CategoryInsertRequest categoryRequest) {
		return ResponseEntity.ok(categoryService.insertCategory(categoryRequest));
	}

	@PostMapping("/update/status")
	public ResponseEntity<String> updateStatusCategory(@Valid @RequestBody CategoryStatusRequest statusRequest) {
		return ResponseEntity.ok(categoryService.updateStatusCategory(statusRequest));
	}

}
