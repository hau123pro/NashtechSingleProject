package mappers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.reponse.CategoryRespone;import dto.request.CategoryRequest;
import entity.Category;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class CategoryMapper {
	
	@Autowired 
	private UtilMapper utilMapper;
	
	public List<CategoryRespone> convertListToCategoryResponse(List<Category> list){
		return utilMapper.convertToResponseList(list, CategoryRespone.class);
	}
	public List<Category> convertRequestToCategory(List<CategoryRequest> categoryRequests){
		return utilMapper.convertToResponseList(categoryRequests, Category.class);
	}
}
