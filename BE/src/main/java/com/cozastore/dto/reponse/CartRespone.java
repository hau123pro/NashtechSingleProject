package com.cozastore.dto.reponse;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartRespone {
	
	private double firstPrice;
	private double finalPrice;
	
	private Date dateCreate;
	private int quantity;
	private Set<CartItemRespone> itemRespones;
	
}
