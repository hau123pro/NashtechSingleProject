package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import entity.ManytoManyID.OrderItemID;
import entity.ManytoManyID.ProductCategoryId;
import entity.ManytoManyID.ProductFormatID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="product_categories")
public class ProductCategory {
	@EmbeddedId
    private ProductCategoryId id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "product_id",insertable = false, updatable = false)
	@JsonIgnore
	private Product product;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category_id",insertable = false, updatable = false)
	private Category category;
	
	
}
