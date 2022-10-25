package dto.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotNull(message="Cart Id cannot empty")
	private int cartID;
	
	@NotNull(message="Product Id cannot empty")
	private int productID;
	
	@NotNull(message="Format Id cannot empty")
	private int formatID;
	
	@NotNull(message="Quantity cannot empty")
	private int quantity;

}
