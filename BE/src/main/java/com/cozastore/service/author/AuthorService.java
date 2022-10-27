package com.cozastore.service.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.AuthorResponse;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.ReviewRespone;
import com.cozastore.entity.Author;
import com.cozastore.mappers.AuthorMapper;
import com.cozastore.repository.author.IAuthorRepository;
import com.cozastore.utils.constant.Status;
@Service
public class AuthorService implements IAuthorService{
	@Autowired
	IAuthorRepository iAuthorRepository;
	
	@Autowired
	AuthorMapper authorMapper;
	
	@Override
	public HeaderResponse<AuthorResponse> getAuthorByPage(Pageable page){
		Page<Author> authorList=iAuthorRepository.findAll(page);
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("page-total-count", String.valueOf(authorList.getTotalPages()));
        responseHeaders.add("page-total-elements", String.valueOf(authorList.getTotalElements()));
        List<AuthorResponse> respones=authorMapper.convertToResponseList(authorList.getContent());
		HeaderResponse<AuthorResponse> headerResponse=new HeaderResponse<>(respones, responseHeaders);
		return headerResponse;
	}

	@Override
	public HeaderResponse<AuthorResponse> getActiveAuthorByPage(Pageable page) {
		Page<Author> authorList=iAuthorRepository.findByStatus(Status.ACTIVE.getValue(), page);
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("page-total-count", String.valueOf(authorList.getTotalPages()));
        responseHeaders.add("page-total-elements", String.valueOf(authorList.getTotalElements()));
        List<AuthorResponse> respones=authorMapper.convertToResponseList(authorList.getContent());
		HeaderResponse<AuthorResponse> headerResponse=new HeaderResponse<>(respones, responseHeaders);
		return headerResponse;
	}
	
	

	
}
