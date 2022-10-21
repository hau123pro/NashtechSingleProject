package com.example.demo.controller.admin;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;

@RestController
@RequestMapping("/api/v1/admin/author")
@Validated
public class AuthorController {
	@Autowired
	AuthorService authorService;
	
	@GetMapping()
	public List<Author> getAllAuthor(
			@PageableDefault(size=2) Pageable page
			){
			List<Author> list= authorService.getAllAuthor(page).getContent();
			return list;
	}
	
	
}
