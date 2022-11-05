package com.cozastore.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/v1/admin/author")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AuthorAdminController {

	@Autowired
	IAuthorService authorService;

	@GetMapping("/")
	public ResponseEntity<AuthorPageResponse> getAuthorByPage(@PageableDefault(size = 2) Pageable page) {
		return ResponseEntity.ok(authorService.getAuthorByPage(page));
	}

	@PutMapping(value = "/update", consumes = "multipart/form-data")
	public ResponseEntity<String> updateAuthor(@Valid @ModelAttribute AuthorInfoRequest authorInfoRequest)
			throws IOException {
		return ResponseEntity.ok(authorService.updateAuthor(authorInfoRequest));
	}

	@PostMapping(value = "/insert", consumes = "multipart/form-data")
	public ResponseEntity<String> getAuthorActiveByPag(@Valid @ModelAttribute AuthorInsertRequest authorInsertRequest)
			throws IOException {
		return ResponseEntity.ok(authorService.insertAuthor(authorInsertRequest));
	}

	@PutMapping("/update/status")
	public ResponseEntity<String> updateAuthor(@Valid @RequestBody AuthorStatusRequest authorStatusRequest) {
		return ResponseEntity.ok(authorService.updateStatusAuthor(authorStatusRequest));
	}
}
