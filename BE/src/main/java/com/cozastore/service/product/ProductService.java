package com.cozastore.service.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.FormatProductResponse;
import com.cozastore.dto.reponse.PageResponse;
import com.cozastore.dto.reponse.ProductBaseResponse;
import com.cozastore.dto.reponse.ProductPageInfoResponse;
import com.cozastore.dto.reponse.ProductRespone;
import com.cozastore.dto.request.FilterRequest;
import com.cozastore.dto.request.ProductInfoRequest;
import com.cozastore.dto.request.ProductRequest;
import com.cozastore.dto.request.ProductStatusRequest;
import com.cozastore.entity.Author;
import com.cozastore.entity.Category;
import com.cozastore.entity.Format;
import com.cozastore.entity.Product;
import com.cozastore.entity.ProductFormat;
import com.cozastore.entity.ManytoManyID.ProductFormatID;
import com.cozastore.exception.BadRequestException;
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
import com.cozastore.utils.constant.Status;
import com.cozastore.utils.constant.SuccessString;

@Service
public class ProductService implements IProductService {

	IProductRepository productRepository;

	IProductFormatRepository productFormatRepository;

	ProductMapper productMapper;

	IFormatRepository iFormatRepository;

	IAuthorRepository authorRepository;

	ICloudinaryService cloudinaryService;

	ICategoryRepository categoryRepository;

	FormatMapper formatMapper;

	PageMapper pageMapper;
	
	@Autowired
	public ProductService(IProductRepository productRepository, IProductFormatRepository productFormatRepository,
			ProductMapper productMapper, IFormatRepository iFormatRepository, IAuthorRepository authorRepository,
			ICloudinaryService cloudinaryService, ICategoryRepository categoryRepository, FormatMapper formatMapper,
			PageMapper pageMapper) {
		super();
		this.productRepository = productRepository;
		this.productFormatRepository = productFormatRepository;
		this.productMapper = productMapper;
		this.iFormatRepository = iFormatRepository;
		this.authorRepository = authorRepository;
		this.cloudinaryService = cloudinaryService;
		this.categoryRepository = categoryRepository;
		this.formatMapper = formatMapper;
		this.pageMapper = pageMapper;
	}

	@Override
	public ProductPageInfoResponse getAllProduct(Pageable pageable) {
		Page<Product> products = productRepository.findAll(pageable);

		List<ProductBaseResponse> productRespones = productMapper
				.convertListProductToBaseResponse(products.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(products, pageable.getPageNumber(), pageable.getPageSize());
		ProductPageInfoResponse infoResponse = productMapper.convertListBaseProductToInfoResponse(productRespones,
				pageResponse);
		return infoResponse;
	}

	@Override
	public ProductPageInfoResponse getProductFeature() {
		Page<Product> products = productRepository
				.findAll(PageRequest.of(0, 6, Sort.by(Sort.Direction.DESC, "averageRating")));
		List<ProductBaseResponse> productRespones = productMapper
				.convertListProductToBaseResponse(products.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(products, 0, 6);
		ProductPageInfoResponse infoResponse = productMapper.convertListBaseProductToInfoResponse(productRespones,
				pageResponse);
		return infoResponse;
	}

	@Override
	public ProductPageInfoResponse getAllProductActive(Pageable pageable) {
		Page<Product> products = productRepository.findByStatus(Status.ACTIVE.getValue(), pageable);
		List<ProductBaseResponse> productRespones = productMapper
				.convertListProductToBaseResponse(products.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(products, pageable.getPageNumber(),
				pageable.getPageSize());
		ProductPageInfoResponse infoResponse = productMapper.convertListBaseProductToInfoResponse(productRespones,
				pageResponse);
		return infoResponse;
	}

	@Override
	public ProductPageInfoResponse getProductFilter(Pageable pageable, FilterRequest filter) {
		Page<Product> products = productRepository.findProductsByFilterParams(filter.getCategoryId(),
				filter.getAuthorId(), filter.getFormatId(), filter.getFirstPrice(), filter.getFinalPrice(), pageable);
		List<ProductBaseResponse> productRespones = productMapper
				.convertListProductToBaseResponse(products.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(products, pageable.getPageNumber(),
				pageable.getPageSize());
		ProductPageInfoResponse infoResponse = productMapper.convertListBaseProductToInfoResponse(productRespones,
				pageResponse);
		return infoResponse;
	}

	@Override
	public ProductRespone getProductById(Integer id) {
		if (id == null)
			throw new BadRequestException(ErrorString.PRODUCT_ID_EMPTY);
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ErrorString.PRODUCT_NOT_FOUND));
		List<ProductFormat> formats = productFormatRepository.findByProductId(product.getId());
		List<FormatProductResponse> listFormat = new ArrayList<>();
		Double maxPrice = productRepository.findProductMaxPriceById(product.getId());
		Double minPrice = productRepository.findProductMinPriceById(product.getId());
		for (ProductFormat format : formats) {
			FormatProductResponse formatProductResponse = formatMapper
					.convertToFormatProductResponse(format.getFormat());
			formatProductResponse.setPrice(format.getPrice());
			formatProductResponse.setQuantity(format.getQuantity());
			listFormat.add(formatProductResponse);
		}
		ProductRespone productRespone = productMapper.convertToProductResponse(product, listFormat);
		productRespone.setMaxPrice(maxPrice);
		productRespone.setMinPrice(minPrice);
		return productRespone;
	}

	@Override
	public String updateInfoProduct(ProductInfoRequest infoRequest) throws IOException {
		// TODO Auto-generated method stub
		ProductFormatID productFormatID = ProductFormatID.builder().productID(infoRequest.getProductId())
				.formatID(infoRequest.getFormatId()).build();
		ProductFormat productFormat = productFormatRepository.findById(productFormatID).orElse(null);
		Product product = productRepository.findById(infoRequest.getProductId())
				.orElseThrow(() -> new NotFoundException(ErrorString.PRODUCT_NOT_FOUND));
		Format format = iFormatRepository.findById(infoRequest.getFormatId())
				.orElseThrow(() -> new NotFoundException(ErrorString.FORMAT_NOT_FOUND));
		Author author = authorRepository.findById(infoRequest.getAuthorId())
				.orElseThrow(() -> new NotFoundException(ErrorString.AUTHOR_NOT_FOUND));
		Set<Category> categories = new HashSet<>();
		for (Integer i : infoRequest.getCategoryIds()) {
			Category category = categoryRepository.findById(i)
					.orElseThrow(() -> new NotFoundException(ErrorString.CATEGORY_NOT_FOUND));
			categories.add(category);
		}
		if (author.getId() != product.getAuthor().getId()) {
			product.setAuthor(author);
		}
		product = productMapper.convertRequestToUpdateProduct(infoRequest, product, categories);
		if (productFormat == null) {
			productFormat = ProductFormat.builder().id(productFormatID).product(product).format(format)
					.quantity(infoRequest.getQuantity()).price(infoRequest.getPrice()).build();
		} else {
			productFormat.setPrice(infoRequest.getPrice());
			productFormat.setQuantity(infoRequest.getQuantity());
		}
		Set<ProductFormat> productFormats = new HashSet<>();
		productFormats.add(productFormat);
		product.setProductFormats(productFormats);
		product.setImgUrl(cloudinaryService.upload(infoRequest.getImgFile()));
		productFormatRepository.save(productFormat);
		productRepository.save(product);
		return SuccessString.PRODUCT_UPDATE_SUCCESS;
	}

	@Override
	public String insertProduct(ProductRequest infoRequest) throws IOException {
		// TODO Auto-generated method stub
		Format format = iFormatRepository.findById(infoRequest.getFormatId())
				.orElseThrow(() -> new NotFoundException(ErrorString.FORMAT_NOT_FOUND));
		Author author = authorRepository.findById(infoRequest.getAuthorId())
				.orElseThrow(() -> new NotFoundException(ErrorString.AUTHOR_NOT_FOUND));
		Set<Category> categories = new HashSet<>();
		for (Integer i : infoRequest.getCategoryIds()) {
			Category category = categoryRepository.findById(i)
					.orElseThrow(() -> new NotFoundException(ErrorString.CATEGORY_NOT_FOUND));
			categories.add(category);
		}
		Product product = productMapper.convertRequestToInsertProduct(infoRequest);
		product.setAuthor(author);
		product.setImgUrl(cloudinaryService.upload(infoRequest.getImgFile()));
		product = productRepository.save(product);
		product = productMapper.convertRequestToProduct(infoRequest, product, categories);
		ProductFormat productFormat = ProductFormat.builder().product(product).format(format)
				.quantity(infoRequest.getQuantity()).price(infoRequest.getPrice()).build();
		ProductFormatID productFormatID = ProductFormatID.builder().productID(productFormat.getProduct().getId())
				.formatID(infoRequest.getFormatId()).build();
		productFormat.setId(productFormatID);
		Set<ProductFormat> productFormats = new HashSet<>();
		productFormats.add(productFormat);
		product.setProductFormats(productFormats);
		productRepository.save(product);
		productFormatRepository.save(productFormat);
		return SuccessString.PRODUCT_INSERT_SUCCESS;
	}

	@Override
	public String updateStatusProduct(ProductStatusRequest productStatusRequest) {
		Product product = productRepository.findById(productStatusRequest.getId())
				.orElseThrow(() -> new NotFoundException(ErrorString.PRODUCT_NOT_FOUND));
		product.setStatus(productStatusRequest.getStatus());
		return SuccessString.PRODUCT_UPDATE_STATUS_SUCCESS;
	}

}
