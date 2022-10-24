package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.constant.Role;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int userID;
	
	@Column(name="email")
	private String email;
	
	@Column(name="user_name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Column(name="phone")
	private String phone;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="date_create")
	private Date dateCreate;
	
	@Column(name="status")
	private String status;
	
//	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@Column(name="role")
    private Integer roles;
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Orders> orders;
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Set<Review> listReview;
	
	 @OneToOne(mappedBy = "user")
	    private WishList wishList;
	
	 @OneToOne(mappedBy = "user")
	    private Cart cart;
	
	
}
