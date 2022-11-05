package com.cozastore.dto.reponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderPageResponse {

	private List<OrderRespone> orderRespones;

	private PageResponse pageResponse;

}
