package com.cozastore.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.AuthorPageResponse;
import com.cozastore.dto.reponse.AuthorResponse;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.request.AuthorInfoRequest;
import com.cozastore.dto.request.AuthorInsertRequest;
import com.cozastore.dto.request.AuthorStatusRequest;
import com.cozastore.service.author.IAuthorService;

@RestController
@RequestMapping("/v1/author")
public class AuthorController {
	@Autowired
	IAuthorService authorService;

	@GetMapping("/active")
	public ResponseEntity<AuthorPageResponse> getAuthorActiveByPage( @PageableDefault(size=2) Pageable page ){
		return ResponseEntity.ok(authorService.getAuthorByPage(page));
	}
	@GetMapping("/active/all")
	public ResponseEntity<List<AuthorResponse>> getAllAuthorActive(){
		return ResponseEntity.ok(authorService.getAllActiveAuthor());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AuthorResponse> getAuthorActiveById(@PathVariable Integer id) {
		AuthorResponse authorResponse = authorService.getAuthorById(id);
		return ResponseEntity.ok(authorResponse);
	}
	
}
