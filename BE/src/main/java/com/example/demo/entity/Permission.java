package com.example.demo.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name="permisison")
//public class Permission {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="Id")
//	private int ID;
//	@Column(name="Name")
//	private String name;
//	@Column(name="Description")
//	private String description;
//	@Column(name="Date_Create")
//	private Date dateCreate;
//	@Column(name="Status")
//	private String status;
//	@ManyToMany
//	@JoinTable(
//	  name = "role_permission", 
//	  joinColumns = @JoinColumn(name = "Permission_ID"), 
//	  inverseJoinColumns = @JoinColumn(name = "Role_ID"))
//	@JsonIgnore
//	private Set<Role> listRole;
//}
