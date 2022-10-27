package com.cozastore.service.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.PageResponse;
import com.cozastore.dto.reponse.ProductRespone;
import com.cozastore.dto.request.FilterRequest;
import com.cozastore.dto.request.ProductInfoRequest;
import com.cozastore.dto.request.ProductStatusRequest;
import com.cozastore.entity.Author;
import com.cozastore.entity.Format;
import com.cozastore.entity.Product;
import com.cozastore.entity.ProductFormat;
import com.cozastore.entity.ManytoManyID.ProductFormatID;
import com.cozastore.exception.BadRequestException;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.ProductMapper;
import com.cozastore.repository.author.IAuthorRepository;
import com.cozastore.repository.format.IFormatRepository;
import com.cozastore.repository.product.IProductRepository;
import com.cozastore.repository.productformat.IProductFormatRepository;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.Status;
import com.cozastore.utils.constant.SuccessString;

@Service
public class ProductService implements IProductService {

	@Autowired
	IProductRepository productRepository;

	@Autowired
	IProductFormatRepository productFormatRepository;

	@Autowired
	ProductMapper productMapper;

	@Autowired
	IFormatRepository iFormatRepository;

	@Autowired
	IAuthorRepository authorRepository;

	@Override
	public List<ProductRespone> getAllProduct(Pageable pageable) {
		Page<Product> products = productRepository.findAll(pageable);
		List<ProductRespone> productRespones = new ArrayList<>();
		for (Product product : products.getContent()) {
			ProductRespone productResponse = getProductById(product.getId());
			productResponse.setPageResponse(
					PageResponse.builder().page(pageable.getPageNumber()).size(pageable.getPageSize()).build());
			productRespones.add(productResponse);
		}
		return productRespones;
	}

	@Override
	public List<ProductRespone> getAllProductActive(Pageable pageable) {
		Page<Product> products = productRepository.findByStatus(Status.ACTIVE.getValue(), pageable);
		List<ProductRespone> productRespones = new ArrayList<>();
		for (Product product : products.getContent()) {
			productRespones.add(getProductById(product.getId()));
		}
		return productRespones;
	}

	@Override
	public List<ProductRespone> getProductFilter(Pageable pageable, FilterRequest filter) {
		Page<Product> products = productRepository.findByCategoryId(filter.getCategoryId(), pageable);
		List<ProductRespone> productRespones = new ArrayList<>();
		for (Product product : products.getContent()) {
			ProductRespone productResponse = getProductById(product.getId());
			productResponse.setPageResponse(
					PageResponse.builder().page(pageable.getPageNumber()).size(pageable.getPageSize()).build());
			productRespones.add(productResponse);
		}
		return productRespones;
	}

	@Override
	public ProductRespone getProductById(Integer id) {
		if (id == null)
			throw new BadRequestException(ErrorString.PRODUCT_ID_EMPTY);
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ErrorString.PRODUCT_NOT_FOUND));
		List<ProductFormat> formats = productFormatRepository.findAll();
		List<Format> listFormat = new ArrayList<Format>();
		for (ProductFormat format : formats)
			listFormat.add(format.getFormat());
		ProductRespone productRespone = productMapper.convertToProductResponse(product, listFormat);
		return productRespone;
	}

	@Override
	public String updateInfoProduct(ProductInfoRequest infoRequest) {
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
		if (author.getId() != product.getAuthor().getId()) {
			product.setAuthor(author);
		}
		product = productMapper.convertRequestToUpdateProduct(infoRequest, product);
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
		productFormatRepository.save(productFormat);
		productRepository.save(product);
		return SuccessString.PRODUCT_UPDATE_SUCCESS;
	}

	@Override
	public String insertProduct(ProductInfoRequest infoRequest) {
		// TODO Auto-generated method stub
		Format format = iFormatRepository.findById(infoRequest.getFormatId())
				.orElseThrow(() -> new NotFoundException(ErrorString.FORMAT_NOT_FOUND));
		Author author = authorRepository.findById(infoRequest.getAuthorId())
				.orElseThrow(() -> new NotFoundException(ErrorString.AUTHOR_NOT_FOUND));
		Product product = productMapper.convertRequestToInsertProduct(infoRequest);
		product.setAuthor(author);
		product = productRepository.save(product);
		product = productMapper.convertRequestToProduct(infoRequest, product);
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
