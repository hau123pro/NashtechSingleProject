package mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.reponse.CategoryRespone;
import dto.reponse.FormatRespone;
import dto.reponse.ProductAuthorResponse;
import dto.reponse.ProductRespone;
import entity.Format;
import entity.Product;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class ProductMapper {
	
	@Autowired
	UtilMapper utilMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired 
	FormatMapper formatMapper;
	
	@Autowired
	ReviewMapper reviewMapper;
	
//	public List<ProductRespone> convertToProductResponseList(List<Product> products){
//		return utilMapper.convertToResponseList(products, ProductRespone.class);
//	} 
	public ProductRespone convertToProductResponse(Product product,List<Format> formats) {
		List<CategoryRespone> categoryRespone=categoryMapper.convertListToCategoryResponse(product.getListCategory()
																							.stream()
																							.collect(Collectors.toList()));
		System.out.println("");
		return ProductRespone.builder()
							.Id(product.getId())
							.productName(product.getProductName())
							.imgUrl(product.getImgUrl())
							.authorResponse(
									ProductAuthorResponse.builder()
									.Id(product.getAuthor().getId())
									.authorName(product.getAuthor().getFirstName()+" "+product.getAuthor().getLastName())
									.build()
									)
							.description(product.getDescription())
							.categoryRespones(
											categoryMapper.convertListToCategoryResponse(product.getListCategory()
																								.stream()
																								.collect(Collectors.toList())
																						))
							.formatRespones(formatMapper.convertListFormatToResponse(formats))
							.build();
							
							
	}
}
