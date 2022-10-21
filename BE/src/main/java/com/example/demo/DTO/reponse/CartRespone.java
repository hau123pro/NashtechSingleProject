package com.example.demo.DTO.reponse;

import java.sql.Date;

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
}
