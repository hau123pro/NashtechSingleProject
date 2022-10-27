package com.cozastore.entity.ManytoManyID;

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
	
	@Column(name="product_id")
	private int productID;
	@Column(name="format_id")
	private int formatID;
}
