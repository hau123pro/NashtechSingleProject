package com.cozastore.service.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.AuthorResponse;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.entity.Author;
import com.cozastore.repository.author.IAuthorRepository;


public interface IAuthorService {
	
	public HeaderResponse<AuthorResponse> getAuthorByPage(Pageable pageable);
	
	public HeaderResponse<AuthorResponse> getActiveAuthorByPage(Pageable pageable);
}
