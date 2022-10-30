package com.cozastore.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.AuthorResponse;
import com.cozastore.dto.reponse.CategoryRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.request.AuthorInfoRequest;
import com.cozastore.dto.request.AuthorInsertRequest;
import com.cozastore.dto.request.AuthorStatusRequest;
import com.cozastore.entity.Author;
import com.cozastore.service.author.IAuthorService;

@RestController
@RequestMapping("/v1/author")
public class AuthorController {
	@Autowired
	IAuthorService authorService;
	
	@GetMapping("/admin")
	public ResponseEntity<List<AuthorResponse>> getAuthorByPage( @PageableDefault(size=2) Pageable page ){
		HeaderResponse<AuthorResponse> headerResponse=authorService.getAuthorByPage(page);
		return ResponseEntity.ok().headers(headerResponse.getHeaders()).body(headerResponse.getItems());
	}
	@GetMapping("/active")
	public ResponseEntity<List<AuthorResponse>> getAuthorActiveByPage( @PageableDefault(size=2) Pageable page ){
		HeaderResponse<AuthorResponse> headerResponse=authorService.getActiveAuthorByPage(page);
		return ResponseEntity.ok().headers(headerResponse.getHeaders()).body(headerResponse.getItems());
	}
	@GetMapping("/active/all")
	public ResponseEntity<List<AuthorResponse>> getAllAuthorActive(){
		return ResponseEntity.ok(authorService.getAllActiveAuthor());
	}
	
	@PutMapping(value="/admin/update",consumes="multipart/form-data")
	public ResponseEntity<String> updateAuthor(@Valid @ModelAttribute AuthorInfoRequest authorInfoRequest) throws IOException{
		return ResponseEntity.ok(authorService.updateAuthor(authorInfoRequest));
	}
	@PostMapping(value="/admin/insert",consumes="multipart/form-data")
	public ResponseEntity<String> getAuthorActiveByPag( @Valid @ModelAttribute AuthorInsertRequest authorInsertRequest ) throws IOException{
		return ResponseEntity.ok(authorService.insertAuthor(authorInsertRequest));
	}
	@PutMapping("/admin/update/status")
	public ResponseEntity<String> updateAuthor(@Valid @RequestBody AuthorStatusRequest authorStatusRequest){
		return ResponseEntity.ok(authorService.updateStatusAuthor(authorStatusRequest));
	}
	
}
