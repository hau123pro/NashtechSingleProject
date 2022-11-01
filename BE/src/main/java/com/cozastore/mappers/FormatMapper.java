package com.cozastore.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cozastore.dto.reponse.FormatProductResponse;
import com.cozastore.dto.reponse.FormatRespone;
import com.cozastore.dto.request.FormatInsertRequest;
import com.cozastore.dto.request.FormatRequest;
import com.cozastore.entity.Format;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class FormatMapper {
	
	@Autowired
	private UtilMapper utilMapper;
	@Autowired 
	private ModelMapper mapper;
	
	public List<FormatRespone> convertListFormatToResponse(List<Format> list){
		List<FormatRespone> formatRespones=utilMapper.convertToResponseList(list, FormatRespone.class);
		return formatRespones;
	}
	public Format convertRequestToFormat(FormatRequest formatRequest) {
		return utilMapper.convertToEntity(formatRequest, Format.class);
	}
	public Format convertRequestToInsertFormat(FormatInsertRequest formatRequest) {
		return utilMapper.convertToEntity(formatRequest, Format.class);
	}
	public FormatRespone convertToFormatResponse(Format format) {
		return utilMapper.convertToEntity(format, FormatRespone.class);
	}
	public FormatProductResponse convertToFormatProductResponse(Format format) {
		return FormatProductResponse.builder().id(format.getId()).formatName(format.getFormatName())
				.description(format.getDescription()).build();
	}
}
