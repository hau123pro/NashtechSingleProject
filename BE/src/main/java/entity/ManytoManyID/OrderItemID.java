package entity.ManytoManyID;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemID implements Serializable{
	@Column(name="Order_ID")
	private int orderID;
	@Column(name="Product_ID")
	private int productID;
	@Column(name="Format_ID")
	private int formatID;
}
