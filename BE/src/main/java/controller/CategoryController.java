package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.reponse.CategoryRespone;
import dto.reponse.HeaderResponse;
import dto.request.CategoryInsertRequest;
import dto.request.CategoryRequest;
import dto.request.CategoryStatusRequest;
import service.category.ICategoryService;
import utils.constant.Status;

@RestController
@RequestMapping("/v1/admin/category")
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<CategoryRespone>> getCategoryByPage(Pageable pageable){
		HeaderResponse<CategoryRespone> headerResponse=categoryService.getCategoryByPage(pageable);
		return ResponseEntity.ok().headers(headerResponse.getHeaders()).body(headerResponse.getItems());
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest){
		return ResponseEntity.ok(categoryService.updateCategory(categoryRequest));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryRespone> getCategoryById(@PathVariable Integer id){
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertProduct(@Valid @RequestBody CategoryInsertRequest categoryRequest){
		return ResponseEntity.ok(categoryService.insertCategory(categoryRequest));
	}
	@PostMapping("/update/status")
	public ResponseEntity<String> updateStatusCategory(@Valid @RequestBody CategoryStatusRequest statusRequest){
		return ResponseEntity.ok(categoryService.updateStatusCategory(statusRequest));
	}
}
