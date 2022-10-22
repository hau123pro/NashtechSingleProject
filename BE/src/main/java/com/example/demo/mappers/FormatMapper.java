package com.example.demo.mappers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DTO.reponse.FormatRespone;
import com.example.demo.entity.Format;

public class FormatMapper {
	
	@Autowired
	private UtilMapper utilMapper;
	
	public List<FormatRespone> convertListFormatToResponse(List<Format> list){
		return utilMapper.convertToResponseList(list, FormatRespone.class);
	}
}
