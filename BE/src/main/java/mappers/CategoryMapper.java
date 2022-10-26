package mappers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.reponse.CategoryRespone;
import dto.request.CategoryRequest;
import entity.Category;
import entity.Product;
import entity.ProductCategory;
import entity.ManytoManyID.ProductCategoryId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

	@Autowired
	private UtilMapper utilMapper;

	public List<CategoryRespone> convertListToCategoryResponse(List<Category> list) {
		return utilMapper.convertToResponseList(list, CategoryRespone.class);
	}

	public List<Category> convertRequestToCategory(List<CategoryRequest> categoryRequests) {
		return utilMapper.convertToResponseList(categoryRequests, Category.class);
	}

	public Set<ProductCategory> convertRequestToProductCategory(Set<Category> categories, Product product) {
		Set<ProductCategory> productCategories=new HashSet<>();
		for (Category category : categories) {
			ProductCategoryId id = ProductCategoryId.builder().categoryID(category.getId()).productID(product.getId())
					.build();
			ProductCategory productCategory = ProductCategory.builder().id(id).category(category).product(product)
					.build();
			productCategories.add(productCategory);
		}
		return productCategories;
	}
}
