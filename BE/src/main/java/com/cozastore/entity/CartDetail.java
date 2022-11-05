package com.cozastore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cozastore.entity.ManytoManyID.CartProductFormatId;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "cart_detail")
public class CartDetail {
	@EmbeddedId
	private CartProductFormatId Id;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "cart_id", insertable = false, updatable = false)
	@JsonIgnore
	private Cart cart;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumns({
			@JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false),
			@JoinColumn(name = "format_id", referencedColumnName = "format_id", insertable = false, updatable = false) })
	private ProductFormat productFormat;
	@Column(name = "first_price")
	private double firstPrice;
	@Column(name = "final_price")
	private double finalPrice;
	@Column(name = "quantity")
	private int quantity;
}
