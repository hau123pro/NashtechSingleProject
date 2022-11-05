package com.cozastore.entity.ManytoManyID;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartProductFormatId implements Serializable{
	
		@Column(name="cart_id")
		private int cartId;
		@Column(name="product_id")
		private int productId;
		@Column(name="format_id")
		private int formatId;
	
}
