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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
//@Table(name="role")
//public class Role {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="Id")
//	private int ID;
//	
//	@Column(name="Name")
//	private String name;
//	
//	@Column(name="Description")
//	private String description;
//	
//	@Column(name="Date_Create")
//	private Date dateCreate;
//	
//	@Column(name="Status")
//	private String status;
//	
////	@OneToMany(mappedBy = "role", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
////	@OnDelete(action = OnDeleteAction.CASCADE)
////	private Set<User> user;
//	@ManyToMany
//	@JoinTable(
//	  name = "role_permission", 
//	  joinColumns = @JoinColumn(name = "Role_ID"), 
//	  inverseJoinColumns = @JoinColumn(name = "Permission_ID"))
//	private Set<Permission> listPermission;
//}
