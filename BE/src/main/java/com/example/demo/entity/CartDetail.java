package com.example.demo.entity;

import java.io.Serializable;
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

import com.example.demo.entity.ManytoManyID.CartProductFormatID;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart_detail")
public class CartDetail {
	@EmbeddedId
	private CartProductFormatID Id;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "Cart_ID",insertable = false, updatable = false)
	@JsonIgnore
	private Cart cart;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	 @JoinColumns({
	      @JoinColumn(name = "Product_ID", referencedColumnName = "Product_ID", insertable = false, updatable = false),
	      @JoinColumn(name = "Format_ID", referencedColumnName = "Format_ID", insertable = false, updatable = false)
	    })
	private ProductFormat productFormat;
	@Column(name="Fisrt_Price")
	private double fisrtPrice;
	@Column(name="Final_Price")
	private double finalPrice;
	@Column(name="Quantity")
	private int quantity;
	}
