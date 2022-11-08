package com.cozastore.service.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cozastore.dto.reponse.CategoryPageResponse;
import com.cozastore.dto.reponse.ProductPageInfoResponse;
import com.cozastore.dto.request.CategoryInsertRequest;
import com.cozastore.dto.request.CategoryRequest;
import com.cozastore.dto.request.CategoryStatusRequest;
import com.cozastore.entity.Category;
import com.cozastore.entity.Product;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.CategoryMapper;
import com.cozastore.mappers.PageMapper;
import com.cozastore.repository.category.ICategoryRepository;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.Status;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

	@InjectMocks
	CategoryService categoryService;

	@Mock
	ICategoryRepository categoryRepository;

	@Mock
	CategoryMapper categoryMapper;

	@Spy
	PageMapper pageMapper = new PageMapper();

	@Test
	void findAllByPage_whenDataValid_shouldReturnResponse() {
		Pageable pageable = PageRequest.of(0, 1);
		CategoryPageResponse expect = new CategoryPageResponse();
		List<Category> content = new ArrayList<>();
		Category value = new Category();
		value.setId(10);
		content.add(value);
		Page<Category> page = new PageImpl<>(content);
		when(categoryRepository.findAll(pageable)).thenReturn(page);
		expect.setPageResponse(
				pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(), pageable.getPageSize()));
		expect.setCartRespones(categoryMapper.convertListToCategoryResponse(content));
		assertThat(categoryService.getCategoryByPage(pageable)).usingRecursiveComparison().isEqualTo(expect);
	}

	@Test
	void findAllCategoryActiveByPage_whenDataValid_shouldReturnResponse() {
		Pageable pageable = PageRequest.of(0, 1);
		CategoryPageResponse expect = new CategoryPageResponse();
		List<Category> content = new ArrayList<>();
		Category value = new Category();
		value.setId(10);
		content.add(value);
		Page<Category> page = new PageImpl<>(content);
		when(categoryRepository.findByStatus(Status.ACTIVE.getValue(), pageable)).thenReturn(page);
		expect.setPageResponse(
				pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(), pageable.getPageSize()));
		expect.setCartRespones(categoryMapper.convertListToCategoryResponse(content));
		assertThat(categoryService.getCategoryActiveByPage(pageable)).usingRecursiveComparison().isEqualTo(expect);
	}

	@Test
	void updateCategory_whenCategoryNotFound_shouldThrowException() {
		CategoryRequest categoryRequest = mock(CategoryRequest.class);
		when(categoryRepository.findById(categoryRequest.getId())).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> categoryService.updateCategory(categoryRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.CATEGORY_NOT_FOUND);
	}

	@Test
	void updateCategory_whenDataValid_shouldSaveCategory() {
		CategoryRequest categoryRequest = mock(CategoryRequest.class);
		Category category = mock(Category.class);
		when(categoryRepository.findById(categoryRequest.getId())).thenReturn(Optional.of(category));
		when(category.getStatus()).thenReturn(Status.ACTIVE.getValue());
		when(categoryMapper.convertRequestToCategory(categoryRequest)).thenReturn(category);
		categoryService.updateCategory(categoryRequest);
		verify(categoryRepository).save(category);
	}

	@Test
	void insertCategory_whenDataValid_shouldSaveCategory() {
		CategoryInsertRequest categoryRequest = mock(CategoryInsertRequest.class);
		Category category = mock(Category.class);
		when(categoryMapper.convertRequestToInsertCategory(categoryRequest)).thenReturn(category);
		categoryService.insertCategory(categoryRequest);
		verify(category).setStatus(Status.ACTIVE.getValue());
		verify(categoryRepository).save(category);
	}

	@Test
	void updateStatusCategory_whenCategoryNotFound_shouldThrowException() {
		CategoryStatusRequest categoryRequest = mock(CategoryStatusRequest.class);
		when(categoryRepository.findById(categoryRequest.getId())).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> categoryService.updateStatusCategory(categoryRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.CATEGORY_NOT_FOUND);
	}

	@Test
	void updateStatusCategory_whenDataValid_shouldSavecategory() {
		CategoryStatusRequest categoryRequest = CategoryStatusRequest.builder().id(1).status(Status.ACTIVE).build();
		Category category = mock(Category.class);
		when(categoryRepository.findById(categoryRequest.getId())).thenReturn(Optional.of(category));
		categoryService.updateStatusCategory(categoryRequest);
		verify(category).setStatus(categoryRequest.getStatus().getValue());

	}

}
