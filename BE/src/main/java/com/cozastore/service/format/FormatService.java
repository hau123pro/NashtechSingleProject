package com.cozastore.service.format;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.FormatPageResponse;
import com.cozastore.dto.reponse.FormatProductResponse;
import com.cozastore.dto.reponse.FormatRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.PageResponse;
import com.cozastore.dto.request.FormatInsertRequest;
import com.cozastore.dto.request.FormatRequest;
import com.cozastore.dto.request.FormatStatusRequest;
import com.cozastore.entity.Format;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.FormatMapper;
import com.cozastore.mappers.PageMapper;
import com.cozastore.repository.format.IFormatRepository;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.Status;
import com.cozastore.utils.constant.SuccessString;

@Service
public class FormatService implements IFormatService {

	@Autowired
	IFormatRepository formatRepository;

	@Autowired
	FormatMapper formatMapper;

	@Autowired
	PageMapper pageMapper;

	@Override
	public FormatPageResponse getFormatByPage(Pageable pageable) {
		Page<Format> page = formatRepository.findAll(pageable);
		List<FormatRespone> formatRespones = formatMapper.convertListFormatToResponse(page.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(),
				pageable.getPageSize());
		return FormatPageResponse.builder().formatRespones(formatRespones).pageResponse(pageResponse).build();
	}

	@Override
	public FormatPageResponse getFormatActiveByPage(Pageable pageable) {
		Page<Format> page = formatRepository.findByStatus(Status.ACTIVE.getValue(), pageable);
		List<FormatRespone> formatRespones = formatMapper.convertListFormatToResponse(page.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(),
				pageable.getPageSize());
		return FormatPageResponse.builder().formatRespones(formatRespones).pageResponse(pageResponse).build();
	}

	@Override
	public List<FormatRespone> getAllFormatActive() {
		List<Format> list = formatRepository.findByStatus(Status.ACTIVE.getValue());
		List<FormatRespone> formatRespones = formatMapper.convertListFormatToResponse(list);
		return formatRespones;
	}

	@Override
	public String updateFormat(FormatRequest formatRequest) {
		Format format = formatRepository.findById(formatRequest.getId())
				.orElseThrow(() -> new NotFoundException(ErrorString.FORMAT_NOT_FOUND));
		int status = format.getStatus();
		format = formatMapper.convertRequestToFormat(formatRequest);
		format.setStatus(status);
		formatRepository.save(format);
		return SuccessString.FORMAT_UPDATE_SUCCESS;
	}

	@Override
	public FormatRespone getFormatById(int id) {
		Format format = formatRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ErrorString.FORMAT_NOT_FOUND));
		FormatRespone formatRespone = formatMapper.convertToFormatResponse(format);
		return formatRespone;
	}

	@Override
	public String insertFormat(FormatInsertRequest formatInsertRequest) {
		Format format = formatMapper.convertRequestToInsertFormat(formatInsertRequest);
		format.setStatus(Status.ACTIVE.getValue());
		formatRepository.save(format);
		return SuccessString.FORMAT_INSERT_SUCCESS;
	}

	@Override
	public String updateStatusFormat(FormatStatusRequest formatStatusRequest) {
		Format format = formatRepository.findById(formatStatusRequest.getId())
				.orElseThrow(() -> new NotFoundException(ErrorString.FORMAT_NOT_FOUND));
		format.setStatus(formatStatusRequest.getStatus().getValue());
		formatRepository.save(format);
		return SuccessString.FORMAT_UPDATE_STATUS_SUCCESS;
	}

}
