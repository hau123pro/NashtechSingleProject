package com.example.demo.entity;

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
import javax.persistence.OneToOne;
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
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int ID;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User_ID", referencedColumnName = "id")
	@JsonIgnore
	private User user;
	
	@Column(name="Total_Price")
	private double firstPrice;
	@Column(name="Final_Price")
	private double finalPrice;
	@Column(name="Date_Create")
	private Date dateCreate;
	@Column(name="Quantity")
	private int quantity;
	
	@OneToMany(mappedBy = "cart",orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<CartDetail> cartDetails;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "Sale_ID")
	@JsonIgnore
	private Sale sale;
}
