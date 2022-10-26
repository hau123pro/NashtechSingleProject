package entity;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="format")
public class Format {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int Id;
	
	@Column(name="name")
	private String formatName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="bonus_price")
	private int bonusPrice;
	
	@Column(name="status")
	private int status;
	
	@OneToMany(mappedBy = "format", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Set<ProductFormat> productFormats;
}
