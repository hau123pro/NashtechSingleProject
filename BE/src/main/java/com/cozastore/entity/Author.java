package com.cozastore.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Builder
@Table(name = "author")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "publish_book")
	private int publishBook;

	@Column(name = "quantity_sale")
	private int quantitySale;

	@Column(name = "description")
	private String description;

	@Column(name = "img_url")
	private String imgUrl;

	@Column(name = "status")
	private int status;

	@OneToMany(mappedBy = "author", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Product> product;
}
