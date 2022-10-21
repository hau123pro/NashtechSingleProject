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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int ID;
	
	@Column(name="Name")
	private String productName;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Img_Url")
	private String imgUrl;
	
	@Column(name="Date_Create")
	private Date dateCreate;
	
	@Column(name="Status")
	private String status;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "Author_ID")
	@JsonIgnore
	private Author author;
	
	
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Review> listReview;
	
	@ManyToMany
	@JoinTable(
	  name = "product_categories", 
	  joinColumns = @JoinColumn(name = "Product_ID"), 
	  inverseJoinColumns = @JoinColumn(name = "Category_ID"))
	private Set<Category> listCategory;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Set<ProductFormat> productFormats;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Set<SaleDetail> listSaleDetail;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Set<WishListDetail> listWishListDetails;
	
	
}
