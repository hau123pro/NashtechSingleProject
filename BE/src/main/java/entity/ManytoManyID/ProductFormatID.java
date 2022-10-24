package entity.ManytoManyID;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductFormatID implements Serializable{
	
	@Column(name="Product_ID")
	private int productID;
	@Column(name="Format_ID")
	private int formatID;
}
