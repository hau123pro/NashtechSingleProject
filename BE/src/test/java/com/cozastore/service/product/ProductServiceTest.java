package com.cozastore.service.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import static org.mockito.Mockito.times;

import com.cozastore.dto.reponse.CategoryRespone;
import com.cozastore.dto.reponse.ProductPageInfoResponse;
import com.cozastore.dto.reponse.ProductPageResponse;
import com.cozastore.dto.reponse.ProductRespone;
import com.cozastore.dto.reponse.UserPageResponse;
import com.cozastore.dto.request.FilterRequest;
import com.cozastore.dto.request.ProductInfoRequest;
import com.cozastore.dto.request.ProductRequest;
import com.cozastore.dto.request.ProductStatusRequest;
import com.cozastore.entity.Author;
import com.cozastore.entity.Category;
import com.cozastore.entity.Format;
import com.cozastore.entity.Product;
import com.cozastore.entity.ProductFormat;
import com.cozastore.entity.Review;
import com.cozastore.entity.User;
import com.cozastore.entity.ManytoManyID.ProductFormatID;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.FormatMapper;
import com.cozastore.mappers.PageMapper;
import com.cozastore.mappers.ProductMapper;
import com.cozastore.repository.author.IAuthorRepository;
import com.cozastore.repository.category.ICategoryRepository;
import com.cozastore.repository.format.IFormatRepository;
import com.cozastore.repository.product.IProductRepository;
import com.cozastore.repository.productformat.IProductFormatRepository;
import com.cozastore.service.cloudinary.ICloudinaryService;
import com.cozastore.utils.constant.ErrorString;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@InjectMocks
	ProductService productService;

	@Mock
	IProductRepository productRepository;

	@Mock
	IProductFormatRepository productFormatRepository;

	@Mock
	ProductMapper productMapper;

	@Mock
	IFormatRepository iFormatRepository;

	@Mock
	IAuthorRepository authorRepository;

	@Mock
	ICloudinaryService cloudinaryService;

	@Mock
	ICategoryRepository categoryRepository;

	@Mock
	FormatMapper formatMapper;

	@Spy
	PageMapper pageMapper = new PageMapper();

	@Test
	void findAllByPage_whenDataValid_shouldReturnResponse() {
		Pageable pageable = PageRequest.of(0, 1);
		ProductPageInfoResponse expect = new ProductPageInfoResponse();
		List<Product> content = new ArrayList<>();
		Product value = new Product();
		value.setId(10);
		content.add(value);
		Page<Product> page = new PageImpl<>(content);
		when(productRepository.findAll(pageable)).thenReturn(page);
		expect.setPageResponse(
				pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(), pageable.getPageSize()));
		expect.setListProduct(productMapper.convertListProductToBaseResponse(content));
		assertThat(productService.getAllProduct(pageable)).usingRecursiveComparison().isEqualTo(expect);
	}

	@Test
	void findProductFeatureByPage_whenDataValid_shouldReturnResponse() {
		Pageable pageable = PageRequest.of(0, 6, Sort.by(Sort.Direction.DESC, "averageRating"));
		ProductPageInfoResponse expect = new ProductPageInfoResponse();
		List<Product> content = new ArrayList<>();
		Product value = new Product();
		value.setId(10);
		content.add(value);
		Page<Product> page = new PageImpl<>(content);
		when(productRepository.findAll(pageable)).thenReturn(page);
		expect.setPageResponse(
				pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(), pageable.getPageSize()));
		expect.setListProduct(productMapper.convertListProductToBaseResponse(content));
		assertThat(productService.getProductFeature()).usingRecursiveComparison().isEqualTo(expect);
	}

	@Test
	void findProductFilterByPage_whenDataValid_shouldReturnResponse() {
		Pageable pageable = PageRequest.of(0, 1);
		FilterRequest filter = mock(FilterRequest.class);
		ProductPageInfoResponse expect = new ProductPageInfoResponse();
		List<Product> content = new ArrayList<>();
		Product value = new Product();
		value.setId(10);
		content.add(value);
		Page<Product> page = new PageImpl<>(content);
		when(productRepository.findAll(pageable)).thenReturn(page);
		expect.setPageResponse(
				pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(), pageable.getPageSize()));
		expect.setListProduct(productMapper.convertListProductToBaseResponse(content));
		assertThat(productService.getAllProduct(pageable)).usingRecursiveComparison().isEqualTo(expect);
	}

	@Test
	void findProductById_whenProductNotFound_shouldReturnBadRequest() {
		when(productRepository.findById(10)).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> productService.getProductById(10));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.PRODUCT_NOT_FOUND);
	}

	@Test
	void insertSingleProduct_whenFormatNotFound_shouldReturnNotFoundException() {
		ProductRequest infoRequest = mock(ProductRequest.class);
		when(iFormatRepository.findById(0)).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> productService.insertProduct(infoRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.FORMAT_NOT_FOUND);
	}
	
	@Test
	void insertSingleProduct_whenAuthorNotFound_shouldReturnNotFoundException() {
		ProductRequest infoRequest = mock(ProductRequest.class);
		Format format=mock(Format.class);
		when(iFormatRepository.findById(0)).thenReturn(Optional.of(format));
		when(authorRepository.findById(0)).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> productService.insertProduct(infoRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.AUTHOR_NOT_FOUND);
	}
	
	@Test
	void insertSingleProduct_whenCategoryNotFound_shouldReturnNotFoundException() {
		ProductRequest infoRequest = new ProductRequest();
		Format format=mock(Format.class);
		Author author=mock(Author.class);
		List<Integer> categoryIds= new ArrayList<>();
		categoryIds.add(1);
		infoRequest.setCategoryIds(categoryIds);
		ArgumentCaptor<ProductFormat> productFormatCaptor=ArgumentCaptor.forClass(ProductFormat.class);
		when(iFormatRepository.findById(0)).thenReturn(Optional.of(format));
		when(authorRepository.findById(0)).thenReturn(Optional.of(author));
		when(categoryRepository.findById(1)).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> productService.insertProduct(infoRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.CATEGORY_NOT_FOUND);
	}
	
	
	@Test
	void insertSingleProduct_whenDataValid_shouldSaveProduct() throws IOException {
		ProductRequest infoRequest = mock(ProductRequest.class);
		Product product=mock(Product.class);
		Format format=mock(Format.class);
		Author author=mock(Author.class);
		Category category=mock(Category.class);
		ProductFormat productFormat=mock(ProductFormat.class);
		List<Integer> categoryIds= new ArrayList<>();
		List<Category> categories = new ArrayList<>();
		when(iFormatRepository.findById(0)).thenReturn(Optional.of(format));
		when(authorRepository.findById(0)).thenReturn(Optional.of(author));
		when( productRepository.save(product)).thenReturn(product);
		when(productMapper.convertRequestToInsertProduct(infoRequest)).thenReturn(product);
		when(productMapper.convertRequestToProduct(infoRequest, product, categories)).thenReturn(product);
		when(infoRequest.getCategoryIds()).thenReturn(categoryIds);
		when(product.getId()).thenReturn(1);
		productService.insertProduct(infoRequest);
		
		
	}
	
	@Test
	void updateProduct_whenProductNotFound_shouldReturnNotFoundException() {
		ProductInfoRequest infoRequest = mock(ProductInfoRequest.class);
		when(productRepository.findById(infoRequest.getProductId())).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> productService.updateInfoProduct(infoRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.PRODUCT_NOT_FOUND);
	}

	@Test
	void updateProduct_whenFormatNotFound_shouldReturnNotFoundException() {
		ProductInfoRequest infoRequest = mock(ProductInfoRequest.class);
		Product product=mock(Product.class);
		when(productRepository.findById(infoRequest.getProductId())).thenReturn(Optional.of(product));
		when(iFormatRepository.findById(0)).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> productService.updateInfoProduct(infoRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.FORMAT_NOT_FOUND);
	}
	
	@Test
	void updateProduct_whenAuthorNotFound_shouldReturnNotFoundException() {
		ProductInfoRequest infoRequest = mock(ProductInfoRequest.class);
		Format format=mock(Format.class);
		Product product=mock(Product.class);
		when(productRepository.findById(infoRequest.getProductId())).thenReturn(Optional.of(product));
		when(iFormatRepository.findById(0)).thenReturn(Optional.of(format));
		when(authorRepository.findById(0)).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() ->productService.updateInfoProduct(infoRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.AUTHOR_NOT_FOUND);
	}
	@Test
	void updateProduct_whenCategoryNotFound_shouldThrowNotFoundException() {
		ProductInfoRequest infoRequest = new ProductInfoRequest();
		Product product=mock(Product.class);
		Format format=mock(Format.class);
		Author author=mock(Author.class);
		List<Integer> categoryIds= new ArrayList<>();
		categoryIds.add(1);
		infoRequest.setCategoryIds(categoryIds);
		when(productRepository.findById(infoRequest.getProductId())).thenReturn(Optional.of(product));
		when(iFormatRepository.findById(infoRequest.getFormatId())).thenReturn(Optional.of(format));
		when(authorRepository.findById(0)).thenReturn(Optional.of(author));
		when(categoryRepository.findById(1)).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> productService.updateInfoProduct(infoRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.CATEGORY_NOT_FOUND);
	}
	
	@Test
	void updateProduct_whenDataValid_shouldSaveProduct() throws IOException {
		ProductRequest infoRequest = mock(ProductRequest.class);
		Product product=mock(Product.class);
		Format format=mock(Format.class);
		Author author=mock(Author.class);
		ProductFormatID productFormatId=mock(ProductFormatID.class);
		ArgumentCaptor<ProductFormat> argumentCaptor=ArgumentCaptor.forClass(ProductFormat.class);
		Category category=mock(Category.class);
		ProductFormat productFormat=mock(ProductFormat.class);
		List<Integer> categoryIds= new ArrayList<>();
		List<Category> categories = new ArrayList<>();
		when(iFormatRepository.findById(0)).thenReturn(Optional.of(format));
		when(authorRepository.findById(0)).thenReturn(Optional.of(author));
		when( productRepository.save(product)).thenReturn(product);
		when(productMapper.convertRequestToInsertProduct(infoRequest)).thenReturn(product);
		when(productMapper.convertRequestToProduct(infoRequest, product, categories)).thenReturn(product);
		when(infoRequest.getCategoryIds()).thenReturn(categoryIds);
		when(product.getId()).thenReturn(1);
		productService.insertProduct(infoRequest);
		verify(product).setImgUrl(cloudinaryService.upload(infoRequest.getImgFile()));
		verify(productRepository, times(2)).save(product);
		verify(productFormatRepository).save(argumentCaptor.capture());
	}
	
	@Test
	void updateStatusProduct_whenProductNotFound_shouldThrowNotFoundException() throws IOException {
		Product product=mock(Product.class);
		ProductStatusRequest productStatusRequest=mock(ProductStatusRequest.class);
		when(productRepository.findById(productStatusRequest.getId())).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> productService.updateStatusProduct(productStatusRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.PRODUCT_NOT_FOUND);
	}
	
	@Test
	void updateStatusProduct_whenDataValid_shouldSaveStatus() throws IOException {
		Product product=mock(Product.class);
		ProductStatusRequest productStatusRequest=mock(ProductStatusRequest.class);
		when(productRepository.findById(0)).thenReturn(Optional.of(product));
		productService.updateStatusProduct(productStatusRequest);
		verify(productRepository).save(product);
	}
	
}
