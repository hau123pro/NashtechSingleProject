package com.cozastore.service.author;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.AuthorResponse;
import com.cozastore.dto.reponse.FormatRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.request.AuthorInfoRequest;
import com.cozastore.dto.request.AuthorInsertRequest;
import com.cozastore.dto.request.AuthorStatusRequest;
import com.cozastore.dto.request.FormatInsertRequest;
import com.cozastore.dto.request.FormatRequest;
import com.cozastore.dto.request.FormatStatusRequest;
import com.cozastore.entity.Author;
import com.cozastore.repository.author.IAuthorRepository;


public interface IAuthorService {
	
	public HeaderResponse<AuthorResponse> getAuthorByPage(Pageable pageable);
	
	public HeaderResponse<AuthorResponse> getActiveAuthorByPage(Pageable pageable);
	
	public List<AuthorResponse> getAllActiveAuthor();
	
	public String updateAuthor(AuthorInfoRequest authorInfoRequest) throws IOException;

	public AuthorResponse getAuthorById(int id);

	public String insertAuthor(AuthorInsertRequest authorInsertRequest) throws IOException;

	public String updateStatusAuthor(AuthorStatusRequest authorStatusRequest);
}
