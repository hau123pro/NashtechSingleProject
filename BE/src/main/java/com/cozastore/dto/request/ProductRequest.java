package com.cozastore.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

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
public class ProductRequest {
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
