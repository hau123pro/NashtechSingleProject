package service.category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import dto.reponse.CategoryRespone;
import dto.reponse.HeaderResponse;
import dto.reponse.OrderRespone;
import dto.request.CategoryInsertRequest;
import dto.request.CategoryRequest;
import dto.request.CategoryStatusRequest;
import entity.Category;
import utils.constant.Status;

@Repository
public interface ICategoryService {
	
	public HeaderResponse<CategoryRespone> getCategoryByPage(Pageable pageable);
	
	public String updateCategory(CategoryRequest categoryRequest);
	
	public CategoryRespone getCategoryById(int id);
	
	public String insertCategory(CategoryInsertRequest categoryRequest);
	
	public String updateStatusCategory(CategoryStatusRequest categoryStatusRequest);
}
