package com.cozastore.service.author;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.AuthorPageResponse;
import com.cozastore.dto.reponse.AuthorResponse;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.PageResponse;
import com.cozastore.dto.request.AuthorInfoRequest;
import com.cozastore.dto.request.AuthorInsertRequest;
import com.cozastore.dto.request.AuthorStatusRequest;
import com.cozastore.entity.Author;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.AuthorMapper;
import com.cozastore.mappers.PageMapper;
import com.cozastore.repository.author.IAuthorRepository;
import com.cozastore.service.cloudinary.ICloudinaryService;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.Status;
import com.cozastore.utils.constant.SuccessString;

@Service
public class AuthorService implements IAuthorService {
	IAuthorRepository authorRepository;

	AuthorMapper authorMapper;

	PageMapper pageMapper;

	ICloudinaryService cloudinaryService;

	@Autowired
	public AuthorService(IAuthorRepository authorRepository, AuthorMapper authorMapper, PageMapper pageMapper,
			ICloudinaryService cloudinaryService) {
		super();
		this.authorRepository = authorRepository;
		this.authorMapper = authorMapper;
		this.pageMapper = pageMapper;
		this.cloudinaryService = cloudinaryService;
	}

	@Override
	public AuthorPageResponse getAuthorByPage(Pageable page) {
		Page<Author> authorList = authorRepository.findAll(page);
		List<AuthorResponse> authorResponses = authorMapper.convertToResponseList(authorList.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(authorList, page.getPageNumber(),
				page.getPageSize());
		return AuthorPageResponse.builder().authorResponses(authorResponses).pageResponse(pageResponse).build();
	}

	@Override
	public AuthorPageResponse getActiveAuthorByPage(Pageable page) {
		Page<Author> authorList = authorRepository.findByStatus(Status.ACTIVE.getValue(), page);
		List<AuthorResponse> authorResponses = authorMapper.convertToResponseList(authorList.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(authorList, page.getPageNumber(),
				page.getPageSize());
		return AuthorPageResponse.builder().authorResponses(authorResponses).pageResponse(pageResponse).build();
	}

	@Override
	public List<AuthorResponse> getAllActiveAuthor() {
		List<Author> authorList = authorRepository.findByStatus(Status.ACTIVE.getValue());
		List<AuthorResponse> respones = authorMapper.convertToResponseList(authorList);
		return respones;
	}

	@Override
	public String updateAuthor(AuthorInfoRequest authorInfoRequest) throws IOException {
		Author author = authorRepository.findById(authorInfoRequest.getId())
				.orElseThrow(() -> new NotFoundException(ErrorString.AUTHOR_NOT_FOUND));
		author = authorMapper.convertRequestToEntity(authorInfoRequest);
		author.setImgUrl(cloudinaryService.upload(authorInfoRequest.getImgFile()));
		authorRepository.save(author);
		return SuccessString.AUTHOR_UPDATE_SUCCESS;
	}

	@Override
	public AuthorResponse getAuthorById(int id) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ErrorString.AUTHOR_NOT_FOUND));
		AuthorResponse authorResponse = authorMapper.convertToAuthorResponse(author);
		return authorResponse;
	}

	@Override
	public String insertAuthor(AuthorInsertRequest authorInsertRequest) throws IOException {
		Author author = authorMapper.convertRequestToInsertEntity(authorInsertRequest);
		author.setStatus(Status.ACTIVE.getValue());
		author.setImgUrl(cloudinaryService.upload(authorInsertRequest.getImgFile()));
		authorRepository.save(author);
		return SuccessString.AUTHOR_INSERT_SUCCESS;
	}

	@Override
	public String updateStatusAuthor(AuthorStatusRequest authorStatusRequest) {
		Author author = authorRepository.findById(authorStatusRequest.getId())
				.orElseThrow(() -> new NotFoundException(ErrorString.AUTHOR_NOT_FOUND));
		author.setStatus(authorStatusRequest.getStatus().getValue());
		authorRepository.save(author);
		return SuccessString.AUTHOR_UPDATE_STATUS_SUCCESS;
	}

}
