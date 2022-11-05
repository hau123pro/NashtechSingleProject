package com.cozastore.dto.reponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormatPageResponse {
	
	private List<FormatRespone> formatRespones;
	
	private PageResponse pageResponse;
	
}
