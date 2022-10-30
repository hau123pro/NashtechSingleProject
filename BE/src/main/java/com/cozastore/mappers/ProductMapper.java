package com.cozastore.mappers;

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

import com.cozastore.dto.reponse.AuthorResponse;
import com.cozastore.dto.reponse.CategoryRespone;
import com.cozastore.dto.reponse.FormatRespone;
import com.cozastore.dto.reponse.ProductRespone;
import com.cozastore.dto.request.ProductInfoRequest;
import com.cozastore.dto.request.ProductRequest;
import com.cozastore.entity.Category;
import com.cozastore.entity.Format;
import com.cozastore.entity.Product;
import com.cozastore.entity.ProductCategory;
import com.cozastore.utils.constant.Status;

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
	
	
	public ProductRespone convertToProductResponse(Product product,List<Format> formats) {
		List<ProductCategory> productCategories=product.getListCategory()
				.stream().collect(Collectors.toList());
		List<Category> categories=new ArrayList<>();
		for(ProductCategory productCategory:productCategories) {
			categories.add(productCategory.getCategory());
		}
		List<CategoryRespone> categoryRespone=categoryMapper.convertListToCategoryResponse(categories);
		return ProductRespone.builder()
							.Id(product.getId())
							.productName(product.getProductName())
							.imgUrl(product.getImgUrl())
							.authorResponse(
									AuthorResponse.builder()
									.id(product.getAuthor().getId())
									.firstName(product.getAuthor().getFirstName())
									.lastName(product.getAuthor().getLastName())
									.description(product.getAuthor().getDescription())
									.imgUrl(product.getAuthor().getImgUrl())
									.publishBook(product.getAuthor().getPublishBook())
									.quantitySale(product.getAuthor().getQuantitySale())
									.build()
									)
							.description(product.getDescription())
							.categoryRespones(categoryRespone)
							.formatRespones(formatMapper.convertListFormatToResponse(formats))
							.reviewRespones(reviewMapper.convertToListReviewResponse(product.getListReview()
																							.stream()
																							.collect(Collectors.toList())
									))
							.build();
							
							
	}
	public Product convertRequestToUpdateProduct(ProductInfoRequest infoRequest,Product product) {
		Date date = Date.valueOf(LocalDate.now());
		Set<Category> categories=new HashSet<>(categoryMapper.convertRequestToCategoryList(infoRequest.getCategoryRequests()));
		Set<ProductCategory> productCategories=categoryMapper.convertRequestToProductCategory(categories, product);
		return Product.builder().Id(infoRequest.getProductId())
								.description(infoRequest.getDescription())
								.productName(infoRequest.getProductName())
								.dateCreate(product.getDateCreate())
								.imgUrl(product.getImgUrl())
								.dateUpdate(date)
								.status(product.getStatus())
								.author(product.getAuthor())
								.listCategory(productCategories)
								.listReview(product.getListReview())
								.listSaleDetail(product.getListSaleDetail())
								.listWishListDetails(product.getListWishListDetails())
								.build();
	}
	public Product convertRequestToProduct(ProductRequest infoRequest,Product product) {
		Date date = Date.valueOf(LocalDate.now());
		Set<Category> categories=new HashSet<>(categoryMapper.convertRequestToCategoryList(infoRequest.getCategoryRequests()));
		Set<ProductCategory> productCategories=categoryMapper.convertRequestToProductCategory(categories, product);
		return Product.builder()
								.Id(product.getId())
								.description(infoRequest.getDescription())
								.productName(infoRequest.getProductName())
								.imgUrl(product.getImgUrl())
								.dateCreate(date)
								.dateUpdate(date)
								.author(product.getAuthor())
								.listCategory(productCategories)
								.status(Status.ACTIVE.getValue())
								.build();
	}
	public Product convertRequestToInsertProduct(ProductRequest infoRequest) {
		Date date = Date.valueOf(LocalDate.now());
		Set<Category> categories=new HashSet<>(categoryMapper.convertRequestToCategoryList(infoRequest.getCategoryRequests()));
		return Product.builder()
								.description(infoRequest.getDescription())
								.productName(infoRequest.getProductName())
								.dateCreate(date)
								.dateUpdate(date)
								.status(Status.ACTIVE.getValue())
								.build();
	}
	
}
