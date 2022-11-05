package com.cozastore.service.category;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.cozastore.dto.reponse.CategoryPageResponse;
import com.cozastore.dto.reponse.CategoryRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.request.CategoryInsertRequest;
import com.cozastore.dto.request.CategoryRequest;
import com.cozastore.dto.request.CategoryStatusRequest;

@Repository
public interface ICategoryService {
	
	public CategoryPageResponse getCategoryByPage(Pageable pageable);
	
	public CategoryPageResponse getCategoryActiveByPage(Pageable pageable);
	
	public List<CategoryRespone> getAllCategoryActive();
	
	public String updateCategory(CategoryRequest categoryRequest);
	
	public CategoryRespone getCategoryById(int id);
	
	public String insertCategory(CategoryInsertRequest categoryRequest);
	
	public String updateStatusCategory(CategoryStatusRequest categoryStatusRequest);
}
