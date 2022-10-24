package service.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dto.reponse.ProductRespone;
import entity.Format;
import entity.Product;
import entity.ProductFormat;
import exception.BadRequestException;
import mappers.ProductMapper;
import repository.product.IProductRepository;
import repository.productformat.IProductFormatRepository;

@Service
public class ProductService implements IProductService {

	@Autowired
	IProductRepository productRepository;

	@Autowired
	IProductFormatRepository productFormatRepository;

	@Autowired
	ProductMapper productMapper;

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
	public ProductRespone getProductById(Integer id) {
		// TODO Auto-generated method stub
		if(id==null)
			throw new BadRequestException("Id cannot be empty");
		Product product=productRepository.findById(id)
										.orElseThrow(()->new BadRequestException("Product not found"));
		List<ProductFormat> formats = productFormatRepository.findAll();
		List<Format> listFormat = new ArrayList<Format>();
		for (ProductFormat format : formats) {
			listFormat.add(format.getFormat());
		}
		ProductRespone productRespone = productMapper.convertToProductResponse(product, listFormat);
		return productRespone;
	}
	

}
