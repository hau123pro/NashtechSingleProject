package com.cozastore.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PageResponse {
	
	private int size;
	
	private int page;
	
	private int totalPage;
	
	private long totalElement;
}
	