package com.cozastore.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.cozastore.dto.reponse.PageResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PageMapper {
	@Autowired
	private UtilMapper utilMapper;

	public <T> PageResponse convertPagetoPageResponse(Page<T> page, int pageNumber, int pageSize) {
		PageResponse pageResponse = PageResponse.builder().page(pageNumber).size(pageSize)
				.totalPage(page.getTotalPages()).totalElement(page.getTotalElements()).build();
		return pageResponse;
	}
}
