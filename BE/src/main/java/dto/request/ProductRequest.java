package dto.request;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import entity.Author;

public class ProductRequest {
private String productName;
	
	private String description;
	
	private String imgUrl;
	
	private Date dateCreate;
	
	private String status;
	
	private int authorId;
}
