package service.format;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

import dto.reponse.CategoryRespone;
import dto.reponse.FormatRespone;
import dto.reponse.HeaderResponse;
import dto.request.CategoryInsertRequest;
import dto.request.CategoryRequest;
import dto.request.CategoryStatusRequest;
import dto.request.FormatInsertRequest;
import dto.request.FormatRequest;
import dto.request.FormatStatusRequest;
import entity.Category;
import entity.Format;
import exception.BadRequestException;
import mappers.FormatMapper;
import repository.format.IFormatRepository;
import utils.constant.ErrorString;
import utils.constant.Status;
import utils.constant.SuccessString;

public class FormatService implements IFormatService{
	
	@Autowired
	IFormatRepository formatRepository;
	
	@Autowired
	FormatMapper formatMapper;

	@Override
	public HeaderResponse<FormatRespone> getFormatByPage(Pageable pageable) {
		Page<Format> page = formatRepository.findAll(pageable);
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("page-total-count", String.valueOf(page.getTotalPages()));
        responseHeaders.add("page-total-elements", String.valueOf(page.getTotalElements()));
		List<FormatRespone> formatRespones = formatMapper.convertListFormatToResponse(page.getContent());
		HeaderResponse<FormatRespone> headerResponse=new HeaderResponse<>(formatRespones, responseHeaders);
		return headerResponse;
	}

	@Override
	public String updateFormat(FormatRequest formatRequest) {
		Format format=formatRepository.findById(formatRequest.getId())
				.orElseThrow(() -> new BadRequestException(ErrorString.FORMAT_NOT_FOUND));
		int status=format.getStatus();
		format =formatMapper.convertRequestToFormat(formatRequest);
		format.setStatus(status);
		formatRepository.save(format);
		return SuccessString.FORMAT_UPDATE_SUCCESS;
	}

	@Override
	public FormatRespone getFormatById(int id) {
		Format format=formatRepository.findById(id)
				.orElseThrow(() -> new BadRequestException(ErrorString.FORMAT_NOT_FOUND));
		FormatRespone formatRespone=formatMapper.convertToFormatResponse(format);
		return formatRespone;
	}

	@Override
	public String insertFormat(FormatInsertRequest formatInsertRequest) {
		Format format=formatMapper.convertRequestToInsertFormat(formatInsertRequest);
		format.setStatus(Status.ACTIVE.getValue());
		formatRepository.save(format);
		return SuccessString.FORMAT_INSERT_SUCCESS;
	}

	@Override
	public String updateStatusFormat(FormatStatusRequest formatStatusRequest) {
		Format format=formatRepository.findById(formatStatusRequest.getId())
				.orElseThrow(() -> new BadRequestException(ErrorString.FORMAT_NOT_FOUND));
		format.setStatus(formatStatusRequest.getStatus().getValue());
		formatRepository.save(format);
		return SuccessString.FORMAT_UPDATE_STATUS_SUCCESS;
	}

}
