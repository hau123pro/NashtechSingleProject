package repository.productformat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.ProductFormat;
import entity.ManytoManyID.ProductFormatID;
@Repository
public interface IProductFormatRepository extends JpaRepository<ProductFormat, ProductFormatID>{
//	public List<ProductFormat> findByProductId(Integer productId);

}
