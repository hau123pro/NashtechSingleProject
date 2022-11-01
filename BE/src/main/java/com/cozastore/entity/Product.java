package com.cozastore.entity;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
@Builder
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int Id;
	
	@Column(name="name")
	private String productName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="img_url")
	private String imgUrl;
	
	@Column(name="date_create")
	private Date dateCreate;
	
	@Column(name="date_update")
	private Date dateUpdate;
	
	
	@Column(name="status")
	private int status;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "author_id")
	@JsonIgnore
	private Author author;
	
	@Column(name="average_rating")
	private int averageRating;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Review> listReview;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	private Set<ProductCategory> listCategory;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
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