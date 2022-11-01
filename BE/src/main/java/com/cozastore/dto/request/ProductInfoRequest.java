package com.cozastore.dto.request;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.cozastore.entity.Author;
import com.cozastore.utils.constant.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInfoRequest {
	
	@NotNull(message="Product ID cannot null")
	private int productId;
	
	@NotBlank(message="Product name cannot empty")
	private String productName;

	private String description;
	
	@NotNull(message="Author ID cannot null")
	private int authorId;
		
	@NotNull(message="Format ID cannot null")
	private int formatId;
	
	@NotNull(message="Quantity cannot null")
	private int quantity;
	
	@NotNull(message="Price cannot null")
	private double price;
	
	@NotNull(message="Image file cannot null")
	private MultipartFile imgFile;
	
	@NotNull(message="Category list cannot empty")
	private List<Integer> categoryIds= new ArrayList<>();;
}
