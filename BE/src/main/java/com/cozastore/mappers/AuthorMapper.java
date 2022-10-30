package com.cozastore.mappers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cozastore.dto.reponse.AuthorResponse;
import com.cozastore.dto.request.AuthorInfoRequest;
import com.cozastore.dto.request.AuthorInsertRequest;
import com.cozastore.entity.Author;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthorMapper {
	
	@Autowired
	UtilMapper utilMapper;
	
	public List<AuthorResponse> convertToResponseList(List<Author> authors){
		return utilMapper.convertToResponseList(authors, AuthorResponse.class);
	}
	
	public Author convertRequestToEntity(AuthorInfoRequest authorInfoRequest) {
		return utilMapper.convertToEntity(authorInfoRequest, Author.class);
	}
	public Author convertRequestToInsertEntity(AuthorInsertRequest authorInsertRequest) {
		return utilMapper.convertToEntity(authorInsertRequest, Author.class);
	}
	public AuthorResponse convertToAuthorResponse(Author data) {
		return utilMapper.convertToResponse(data, AuthorResponse.class);
	}
	
}
