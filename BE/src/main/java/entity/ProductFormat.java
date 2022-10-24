package entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import entity.ManytoManyID.ProductFormatID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product_format")
public class ProductFormat {
	@EmbeddedId
    private ProductFormatID id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "Product_ID",insertable = false, updatable = false)
	@JsonIgnore
	private Product product;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "Format_ID",insertable = false, updatable = false)
	private Format format;
	
	@Column(name="Quantity")
	private int quantity;
	
	@Column(name="Price")
	private double price;
}
