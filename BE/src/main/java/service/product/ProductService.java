package service.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import dto.reponse.ProductRespone;
import dto.request.ProductInfoRequest;
import entity.Author;
import entity.Format;
import entity.Product;
import entity.ProductFormat;
import entity.ManytoManyID.ProductFormatID;
import exception.BadRequestException;
import mappers.ProductMapper;
import repository.author.IAuthorRepository;
import repository.format.FormatRepository;
import repository.product.IProductRepository;
import repository.productformat.IProductFormatRepository;
import utils.constant.ErrorString;
import utils.constant.Status;
import utils.constant.SuccessString;

@Service
public class ProductService implements IProductService {

	@Autowired
	IProductRepository productRepository;

	@Autowired
	IProductFormatRepository productFormatRepository;

	@Autowired
	ProductMapper productMapper;

	@Autowired
	FormatRepository formatRepository;

	@Autowired
	IAuthorRepository authorRepository;

	@Override
	public List<ProductRespone> getAllProduct(Pageable pageable) {
		Page<Product> products = productRepository.findAll(pageable);
		List<ProductRespone> productRespones = new ArrayList<>();
		for (Product product : products.getContent()) {
			productRespones.add(getProductById(product.getId()));
		}
		return productRespones;
	}
	@Override
	public List<ProductRespone> getAllProductActive(Pageable pageable) {
		Page<Product> products = productRepository.findByStatus(pageable, Status.ACTIVE.getValue());
		List<ProductRespone> productRespones = new ArrayList<>();
		for (Product product : products.getContent()) {
			productRespones.add(getProductById(product.getId()));
		}
		return productRespones;
	}


	@Override
	public ProductRespone getProductById(Integer id) {
		// TODO Auto-generated method stub
		if (id == null)
			throw new BadRequestException(ErrorString.PRODUCT_ID_EMPTY);
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new BadRequestException(ErrorString.PRODUCT_NOT_FOUND));
		List<ProductFormat> formats = productFormatRepository.findAll();
		List<Format> listFormat = new ArrayList<Format>();
		for (ProductFormat format : formats) {
			listFormat.add(format.getFormat());
		}
		ProductRespone productRespone = productMapper.convertToProductResponse(product, listFormat);
		return productRespone;
	}

	@Override
	@Transactional
	public String updateInfoProduct(ProductInfoRequest infoRequest) {
		// TODO Auto-generated method stub
		ProductFormatID productFormatID = ProductFormatID.builder().productID(infoRequest.getProductId())
				.formatID(infoRequest.getFormatId()).build();
		ProductFormat productFormat = productFormatRepository.findById(productFormatID).orElse(null);
		Product product = productRepository.findById(infoRequest.getProductId())
				.orElseThrow(() -> new BadRequestException(ErrorString.PRODUCT_NOT_FOUND));
		Format format = formatRepository.findById(infoRequest.getFormatId())
				.orElseThrow(() -> new BadRequestException(ErrorString.FORMAT_NOT_FOUND));
		Author author = authorRepository.findById(infoRequest.getAuthorId())
				.orElseThrow(() -> new BadRequestException(ErrorString.AUTHOR_NOT_FOUND));
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
	@Transactional
	public String insertProduct(ProductInfoRequest infoRequest) {
		// TODO Auto-generated method stub
		ProductFormatID productFormatID = ProductFormatID.builder().productID(infoRequest.getProductId())
				.formatID(infoRequest.getFormatId()).build();
		ProductFormat productFormat = productFormatRepository.findById(productFormatID).orElse(null);
		Product product = productRepository.findById(infoRequest.getProductId()).get();
		Format format = formatRepository.findById(infoRequest.getFormatId())
				.orElseThrow(() -> new BadRequestException(ErrorString.FORMAT_NOT_FOUND));
		Author author = authorRepository.findById(infoRequest.getAuthorId())
				.orElseThrow(() -> new BadRequestException(ErrorString.AUTHOR_NOT_FOUND));
		if(product!=null)
			throw new BadRequestException(ErrorString.PRODUCT_EXIST);
		product = productMapper.convertRequestToProduct(infoRequest);
		product.setAuthor(author);
		productFormat = ProductFormat.builder().id(productFormatID).product(product).format(format)
					.quantity(infoRequest.getQuantity()).price(infoRequest.getPrice()).build();
		Set<ProductFormat> productFormats = new HashSet<>();
		productFormats.add(productFormat);
		product.setProductFormats(productFormats);
		productFormatRepository.save(productFormat);
		productRepository.save(product);
		return SuccessString.PRODUCT_INSERT_SUCCESS;
	}

}
