package com.cozastore.service.format;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cozastore.dto.reponse.FormatPageResponse;
import com.cozastore.dto.reponse.FormatRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.request.FormatInsertRequest;
import com.cozastore.dto.request.FormatRequest;
import com.cozastore.dto.request.FormatStatusRequest;

public interface IFormatService {

	public FormatPageResponse getFormatByPage(Pageable pageable);
	
	public FormatPageResponse getFormatActiveByPage(Pageable pageable);
	
	public List<FormatRespone> getAllFormatActive();

	public String updateFormat(FormatRequest formatRequest);

	public FormatRespone getFormatById(int id);

	public String insertFormat(FormatInsertRequest formatInsertRequest);

	public String updateStatusFormat(FormatStatusRequest formatStatusRequest);
}
