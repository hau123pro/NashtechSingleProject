package dto.request;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInfoRequest {
	
	private int productId;
	
	private String productName;

	private String description;

	private String imgUrl;

	private Date dateCreate;

	private int authorId;
		
	private int formatId;
	
	private int quantity;
	
	private double price;

}
