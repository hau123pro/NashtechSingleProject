package com.cozastore.dto.reponse;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class ReviewPageResponse {
	
	List<ReviewRespone> reviewRespones;
	
	PageResponse pageResponse;
}
