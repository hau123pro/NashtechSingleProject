package mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.reponse.AuthorResponse;
import dto.reponse.CategoryRespone;
import dto.reponse.FormatRespone;
import dto.reponse.ProductRespone;
import dto.request.ProductInfoRequest;
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
		return ProductRespone.builder()
							.Id(product.getId())
							.productName(product.getProductName())
							.imgUrl(product.getImgUrl())
							.authorResponse(
									AuthorResponse.builder()
									.Id(product.getAuthor().getId())
									.authorName(product.getAuthor().getFirstName()+" "+product.getAuthor().getLastName())
									.description(product.getAuthor().getDescription())
									.imgUrl(product.getAuthor().getImgUrl())
									.publishBook(product.getAuthor().getPublishBook())
									.quantitySale(product.getAuthor().getQuantitySale())
									.build()
									)
							.description(product.getDescription())
							.categoryRespones(
											categoryMapper.convertListToCategoryResponse(product.getListCategory()
																								.stream()
																								.collect(Collectors.toList())
																						))
							.formatRespones(formatMapper.convertListFormatToResponse(formats))
							.reviewRespones(reviewMapper.convertToListReviewResponse(product.getListReview()
																							.stream()
																							.collect(Collectors.toList())
									))
							.build();
							
							
	}
	public Product convertRequestToProduct(ProductInfoRequest infoRequest) {
		return utilMapper.convertToEntity(infoRequest, Product.class);
	}
}
