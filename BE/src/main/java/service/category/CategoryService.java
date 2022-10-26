package service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import dto.reponse.CategoryRespone;
import dto.reponse.HeaderResponse;
import dto.reponse.OrderRespone;
import dto.request.CategoryInsertRequest;
import dto.request.CategoryRequest;
import dto.request.CategoryStatusRequest;
import entity.Category;
import exception.BadRequestException;
import mappers.CategoryMapper;
import repository.category.ICategoryRepository;
import utils.constant.ErrorString;
import utils.constant.Status;
import utils.constant.SuccessString;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	ICategoryRepository categoryRepository;

	@Autowired
	CategoryMapper categoryMapper;

	@Override
	public HeaderResponse<CategoryRespone> getCategoryByPage(Pageable pageable) {
		Page<Category> page = categoryRepository.findAll(pageable);
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("page-total-count", String.valueOf(page.getTotalPages()));
        responseHeaders.add("page-total-elements", String.valueOf(page.getTotalElements()));
		List<CategoryRespone> categoryResponses = categoryMapper.convertListToCategoryResponse(page.getContent());
		HeaderResponse<CategoryRespone> headerResponse=new HeaderResponse<>(categoryResponses, responseHeaders);
		return headerResponse;
	}

	@Override
	public String updateCategory(CategoryRequest categoryRequest) {
		Category category = categoryRepository.findById(categoryRequest.getId())
				.orElseThrow(() -> new BadRequestException(ErrorString.CATEGORY_NOT_FOUND));
		int status=category.getStatus();
		category = categoryMapper.convertRequestToCategory(categoryRequest);
		category.setStatus(status);
		categoryRepository.save(category);
		return SuccessString.CATEGORY_UPDATE_SUCCESS;
	}

	@Override
	public CategoryRespone getCategoryById(int id) {
		Category category=categoryRepository.findById(id)
				.orElseThrow(()->new BadRequestException(ErrorString.CATEGORY_NOT_FOUND));
		CategoryRespone categoryRespone=categoryMapper.convertCategoryToResponse(category);
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
		Category category=categoryRepository.findById(statusRequest.getId())
				.orElseThrow(()->new BadRequestException(ErrorString.CATEGORY_NOT_FOUND));
		category.setStatus(statusRequest.getStatus().getValue());
		return SuccessString.CATEGORY_UPDATE_STATUS_SUCCESS;
	}
	

}
