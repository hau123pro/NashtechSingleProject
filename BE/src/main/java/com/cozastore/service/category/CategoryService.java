
package com.cozastore.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.CategoryPageResponse;
import com.cozastore.dto.reponse.CategoryRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.PageResponse;
import com.cozastore.dto.request.CategoryInsertRequest;
import com.cozastore.dto.request.CategoryRequest;
import com.cozastore.dto.request.CategoryStatusRequest;
import com.cozastore.entity.Category;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.CategoryMapper;
import com.cozastore.mappers.PageMapper;
import com.cozastore.repository.category.ICategoryRepository;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.Status;
import com.cozastore.utils.constant.SuccessString;

@Service
public class CategoryService implements ICategoryService {

	ICategoryRepository categoryRepository;

	CategoryMapper categoryMapper;

	PageMapper pageMapper;

	@Autowired
	public CategoryService(ICategoryRepository categoryRepository, CategoryMapper categoryMapper,
			PageMapper pageMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
		this.pageMapper = pageMapper;
	}

	@Override
	public CategoryPageResponse getCategoryByPage(Pageable pageable) {
		Page<Category> page = categoryRepository.findAll(pageable);
		List<CategoryRespone> categoryResponses = categoryMapper.convertListToCategoryResponse(page.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(),
				pageable.getPageSize());
		return CategoryPageResponse.builder().pageResponse(pageResponse).cartRespones(categoryResponses).build();
	}

	@Override
	public CategoryPageResponse getCategoryActiveByPage(Pageable pageable) {
		Page<Category> page = categoryRepository.findByStatus(Status.ACTIVE.getValue(), pageable);
		List<CategoryRespone> categoryResponses = categoryMapper.convertListToCategoryResponse(page.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(),
				pageable.getPageSize());
		return CategoryPageResponse.builder().pageResponse(pageResponse).cartRespones(categoryResponses).build();
	}

	@Override
	public List<CategoryRespone> getAllCategoryActive() {
		List<Category> categories = categoryRepository.findByStatus(Status.ACTIVE.getValue());
		List<CategoryRespone> categoryResponses = categoryMapper.convertListToCategoryResponse(categories);
		return categoryResponses;
	}

	@Override
	public String updateCategory(CategoryRequest categoryRequest) {
		Category category = categoryRepository.findById(categoryRequest.getId())
				.orElseThrow(() -> new NotFoundException(ErrorString.CATEGORY_NOT_FOUND));
		int status = category.getStatus();
		category = categoryMapper.convertRequestToCategory(categoryRequest);
		category.setStatus(status);
		categoryRepository.save(category);
		return SuccessString.CATEGORY_UPDATE_SUCCESS;
	}

	@Override
	public CategoryRespone getCategoryById(int id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ErrorString.CATEGORY_NOT_FOUND));
		CategoryRespone categoryRespone = categoryMapper.convertCategoryToResponse(category);
		return categoryRespone;
	}

	@Override
	public String insertCategory(CategoryInsertRequest categoryRequest) {
		Category category = categoryMapper.convertRequestToInsertCategory(categoryRequest);
		category.setStatus(Status.ACTIVE.getValue());
		categoryRepository.save(category);
		return SuccessString.CATEGORY_INSERT_SUCCESS;
	}

	@Override
	public String updateStatusCategory(CategoryStatusRequest statusRequest) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(statusRequest.getId())
				.orElseThrow(() -> new NotFoundException(ErrorString.CATEGORY_NOT_FOUND));
		category.setStatus(statusRequest.getStatus().getValue());
		return SuccessString.CATEGORY_UPDATE_STATUS_SUCCESS;
	}

}
