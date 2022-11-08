package com.cozastore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cozastore.entity.ManytoManyID.ProductFormatID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_format")
@Builder
@EqualsAndHashCode
public class ProductFormat {
	@EmbeddedId
	private ProductFormatID id;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
//	@JsonIgnore
	private Product product;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "format_id", insertable = false, updatable = false)
	private Format format;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private double price;
}
