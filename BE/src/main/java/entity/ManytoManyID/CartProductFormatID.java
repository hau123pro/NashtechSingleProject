package entity.ManytoManyID;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartProductFormatID implements Serializable{
	
		@Column(name="Cart_ID")
		private int cartID;
		@Column(name="Product_ID")
		private int productID;
		@Column(name="Format_ID")
		private int formatID;
	
}
