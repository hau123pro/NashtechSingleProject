package com.cozastore.dto.reponse;

import java.sql.Date;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderRespone {
	private int ID;
	private Date dateCreate;
	private String status;
	private double totalPrice;
	private int discount;
	private int quantity;
}
