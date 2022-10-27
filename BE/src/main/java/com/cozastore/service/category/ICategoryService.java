package com.cozastore.service.category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.cozastore.dto.reponse.CategoryRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.OrderRespone;
import com.cozastore.dto.request.CategoryInsertRequest;
import com.cozastore.dto.request.CategoryRequest;
import com.cozastore.dto.request.CategoryStatusRequest;
import com.cozastore.entity.Category;
import com.cozastore.utils.constant.Status;

@Repository
public interface ICategoryService {
	
	public HeaderResponse<CategoryRespone> getCategoryByPage(Pageable pageable);
	
	public HeaderResponse<CategoryRespone> getCategoryActiveByPage(Pageable pageable);
	
	public String updateCategory(CategoryRequest categoryRequest);
	
	public CategoryRespone getCategoryById(int id);
	
	public String insertCategory(CategoryInsertRequest categoryRequest);
	
	public String updateStatusCategory(CategoryStatusRequest categoryStatusRequest);
}
