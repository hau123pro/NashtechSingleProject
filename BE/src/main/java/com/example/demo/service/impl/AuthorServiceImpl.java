package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
@Service
public class AuthorServiceImpl implements AuthorService{
	@Autowired
	AuthorRepository authorRepository;
	@Override
	public Page<Author> getAllAuthor(int i){
		Page<Author> authorList=authorRepository.findAll(PageRequest.of(i, 2));
		return authorList;
	}

	
}
