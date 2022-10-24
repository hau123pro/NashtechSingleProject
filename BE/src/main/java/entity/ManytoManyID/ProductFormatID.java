package entity.ManytoManyID;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ProductFormatID implements Serializable{
	
	@Column(name="Product_ID")
	private int productID;
	@Column(name="Format_ID")
	private int formatID;
}
