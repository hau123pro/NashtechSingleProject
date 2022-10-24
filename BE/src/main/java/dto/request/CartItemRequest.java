package dto.request;

import javax.persistence.Column;

import entity.ProductFormat;
import entity.ManytoManyID.CartProductFormatID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {
	
	private int cartID;
	
	private int productID;
	
	private int formatID;

	
	
}
