package com.example.demo.controller.admin;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;

@RestController
@RequestMapping("/api/v1/author")
@Validated
public class AuthorController {
	@Autowired
	AuthorService authorService;
	
	@GetMapping()
	public List<Author> getAllAuthor(
			@RequestParam(value="page",defaultValue="1") @Min(value=1,message="Can't set value page less than one") Integer page
			){
			List<Author> list= authorService.getAllAuthor(page-1).getContent();
			return list;
	}
	
}
