package com.cozastore.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.AuthorResponse;
import com.cozastore.dto.reponse.CategoryRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.entity.Author;
import com.cozastore.service.author.IAuthorService;

@RestController
@RequestMapping("/v1/admin/author")
@Validated
public class AuthorController {
	@Autowired
	IAuthorService authorService;
	
	@GetMapping()
	public ResponseEntity<List<AuthorResponse>> getAuthorByPage( @PageableDefault(size=2) Pageable page ){
		HeaderResponse<AuthorResponse> headerResponse=authorService.getAuthorByPage(page);
		return ResponseEntity.ok().headers(headerResponse.getHeaders()).body(headerResponse.getItems());
	}
	@GetMapping("/active")
	public ResponseEntity<List<AuthorResponse>> getAuthorActiveByPage( @PageableDefault(size=2) Pageable page ){
		HeaderResponse<AuthorResponse> headerResponse=authorService.getActiveAuthorByPage(page);
		return ResponseEntity.ok().headers(headerResponse.getHeaders()).body(headerResponse.getItems());
	}
	
}
