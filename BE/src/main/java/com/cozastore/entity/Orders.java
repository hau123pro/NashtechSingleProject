package com.cozastore.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Table(name="orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int ID;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@Column(name="date_create")
	private Date dateCreate;
	
	@Column(name="status")
	private int status;
	
	@Column(name="first_price")
	private double firstPrice;
	@Column(name="final_price")
	private double finalPrice;
	
	@Column(name="discount")
	private int discount;
	
	@Column(name="quantity")
	private int quantity;
	
	@OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<OrderDetail> orderDetails;
}
