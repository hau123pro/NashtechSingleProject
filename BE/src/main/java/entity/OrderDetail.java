package entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import entity.ManytoManyID.CartProductFormatID;
import entity.ManytoManyID.OrderItemID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="order_detail")
public class OrderDetail {
	@EmbeddedId
	private OrderItemID Id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "order_id",insertable = false, updatable = false)
	@JsonIgnore
	private Orders order;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	 @JoinColumns({
	      @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false),
	      @JoinColumn(name = "format_id", referencedColumnName = "format_id", insertable = false, updatable = false)
	    })
	private ProductFormat productFormat;
	
	
	@Column(name="price")
	private double price;
	@Column(name="quantity")
	private int quantity;
}
