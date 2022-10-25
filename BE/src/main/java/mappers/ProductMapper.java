package mappers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.reponse.AuthorResponse;
import dto.reponse.CategoryRespone;
import dto.reponse.FormatRespone;
import dto.reponse.ProductRespone;
import dto.request.ProductInfoRequest;
import entity.Category;
import entity.Format;
import entity.Product;
import lombok.RequiredArgsConstructor;
import utils.constant.Status;
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
	public Product convertRequestToUpdateProduct(ProductInfoRequest infoRequest,Product product) {
		Date date = Date.valueOf(LocalDate.now());
		Set<Category> categories=new HashSet<>(categoryMapper.convertRequestToCategory(infoRequest.getCategoryRequests()));
		return Product.builder().Id(infoRequest.getProductId())
								.imgUrl(infoRequest.getImgUrl())
								.description(infoRequest.getDescription())
								.productName(infoRequest.getProductName())
								.dateCreate(product.getDateCreate())
								.dateUpdate(date)
								.status(product.getStatus())
								.author(product.getAuthor())
								.listCategory(categories)
								.listReview(product.getListReview())
								.listSaleDetail(product.getListSaleDetail())
								.listWishListDetails(product.getListWishListDetails())
								.build();
	}
	public Product convertRequestToProduct(ProductInfoRequest infoRequest) {
		Date date = Date.valueOf(LocalDate.now());
		Set<Category> categories=new HashSet<>(categoryMapper.convertRequestToCategory(infoRequest.getCategoryRequests()));
		return Product.builder().Id(infoRequest.getProductId())
								.imgUrl(infoRequest.getImgUrl())
								.description(infoRequest.getDescription())
								.productName(infoRequest.getProductName())
								.dateCreate(date)
								.dateUpdate(date)
								.listCategory(categories)
								.status(Status.ACTIVE.getValue())
								.build();
	}
}
