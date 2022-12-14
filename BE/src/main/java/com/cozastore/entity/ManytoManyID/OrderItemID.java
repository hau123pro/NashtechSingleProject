package com.cozastore.entity.ManytoManyID;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemID implements Serializable{
	@Column(name="order_id")
	private int orderID;
	@Column(name="product_id")
	private int productID;
	@Column(name="format_id")
	private int formatID;
}
