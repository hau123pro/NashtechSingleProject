package com.cozastore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cozastore.entity.ManytoManyID.ProductFormatID;
import com.cozastore.entity.ManytoManyID.SaleProductID;
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
@Table(name="sale_detail")
public class SaleDetail {
	@EmbeddedId
    private SaleProductID id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "sale_id",insertable = false, updatable = false)
	@JsonIgnore
	private Sale sale;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id",insertable = false, updatable = false)
	private Product product;
	
	@Column(name="invoice")
	private int invoice;
}
