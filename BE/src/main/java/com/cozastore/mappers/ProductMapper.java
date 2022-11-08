package com.cozastore.mappers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cozastore.dto.reponse.AuthorResponse;
import com.cozastore.dto.reponse.CategoryRespone;
import com.cozastore.dto.reponse.FormatProductResponse;
import com.cozastore.dto.reponse.PageResponse;
import com.cozastore.dto.reponse.ProductBaseResponse;
import com.cozastore.dto.reponse.ProductPageInfoResponse;
import com.cozastore.dto.reponse.ProductRespone;
import com.cozastore.dto.request.ProductInfoRequest;
import com.cozastore.dto.request.ProductRequest;
import com.cozastore.entity.Category;
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

	public ProductRespone convertToProductResponse(Product product, List<FormatProductResponse> formats) {
		List<ProductCategory> productCategories = product.getListCategory().stream().collect(Collectors.toList());
		List<Category> categories = new ArrayList<>();
		for (ProductCategory productCategory : productCategories) {
			categories.add(productCategory.getCategory());
		}
		List<CategoryRespone> categoryRespone = categoryMapper.convertListToCategoryResponse(categories);
		return ProductRespone.builder().Id(product.getId()).productName(product.getProductName())
				.imgUrl(product.getImgUrl())
				.authorResponse(AuthorResponse.builder().id(product.getAuthor().getId())
						.firstName(product.getAuthor().getFirstName()).lastName(product.getAuthor().getLastName())
						.description(product.getAuthor().getDescription()).imgUrl(product.getAuthor().getImgUrl())
						.publishBook(product.getAuthor().getPublishBook())
						.quantitySale(product.getAuthor().getQuantitySale()).build())
				.averageRating(product.getAverageRating()).description(product.getDescription())
				.categoryRespones(categoryRespone).formatRespones(formats)
				.reviewRespones(reviewMapper
						.convertToListReviewResponse(product.getListReview().stream().collect(Collectors.toList())))
				.build();

	}

	public Product convertRequestToUpdateProduct(ProductInfoRequest infoRequest, Product product,
			List<Category> categories) {
		Date date = Date.valueOf(LocalDate.now());
		List<ProductCategory> productCategories = categoryMapper.convertRequestToProductCategory(categories, product);
		return Product.builder().id(infoRequest.getProductId()).description(infoRequest.getDescription())
				.productName(infoRequest.getProductName()).dateCreate(product.getDateCreate())
				.imgUrl(product.getImgUrl()).dateUpdate(date).status(product.getStatus()).author(product.getAuthor())
				.listCategory(productCategories).listReview(product.getListReview())
//								.listSaleDetail(product.getListSaleDetail())
//								.listWishListDetails(product.getListWishListDetails())
				.build();
	}

	public Product convertRequestToProduct(ProductRequest infoRequest, Product product, List<Category> categories) {
		Date date = Date.valueOf(LocalDate.now());
		List<ProductCategory> productCategories = categoryMapper.convertRequestToProductCategory(categories, product);
		return Product.builder().id(product.getId()).description(infoRequest.getDescription())
				.productName(infoRequest.getProductName()).imgUrl(product.getImgUrl()).dateCreate(date).dateUpdate(date)
				.author(product.getAuthor()).listCategory(productCategories).status(Status.ACTIVE.getValue()).build();
	}

	public Product convertRequestToInsertProduct(ProductRequest infoRequest) {
		Date date = Date.valueOf(LocalDate.now());
		Product product =Product.builder().description(infoRequest.getDescription()).productName(infoRequest.getProductName())
				.dateCreate(date).dateUpdate(date).status(Status.ACTIVE.getValue()).build();
		return product;
	}

	public List<ProductBaseResponse> convertListProductToBaseResponse(List<Product> products) {
		return utilMapper.convertToResponseList(products, ProductBaseResponse.class);
	}

	public ProductPageInfoResponse convertListBaseProductToInfoResponse(List<ProductBaseResponse> baseResponses,
			PageResponse pageResponse) {
		return ProductPageInfoResponse.builder().listProduct(baseResponses).pageResponse(pageResponse).build();
	}

}
