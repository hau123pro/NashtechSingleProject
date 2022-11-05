package com.cozastore.service.author;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cozastore.dto.reponse.AuthorPageResponse;
import com.cozastore.dto.reponse.AuthorResponse;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.request.AuthorInfoRequest;
import com.cozastore.dto.request.AuthorInsertRequest;
import com.cozastore.dto.request.AuthorStatusRequest;


public interface IAuthorService {
	
	public AuthorPageResponse getAuthorByPage(Pageable pageable);
	
	public AuthorPageResponse getActiveAuthorByPage(Pageable pageable);
	
	public List<AuthorResponse> getAllActiveAuthor();
	
	public String updateAuthor(AuthorInfoRequest authorInfoRequest) throws IOException;

	public AuthorResponse getAuthorById(int id);

	public String insertAuthor(AuthorInsertRequest authorInsertRequest) throws IOException;

	public String updateStatusAuthor(AuthorStatusRequest authorStatusRequest);
}
