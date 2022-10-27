package com.cozastore.mappers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cozastore.dto.reponse.AuthorResponse;
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
}
