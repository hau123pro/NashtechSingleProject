package service.format;

import java.util.List;

import org.springframework.data.domain.Pageable;

import dto.reponse.CategoryRespone;
import dto.reponse.FormatRespone;
import dto.reponse.HeaderResponse;
import dto.request.CategoryInsertRequest;
import dto.request.CategoryRequest;
import dto.request.CategoryStatusRequest;
import dto.request.FormatInsertRequest;
import dto.request.FormatRequest;
import dto.request.FormatStatusRequest;

public interface IFormatService {

	public HeaderResponse<FormatRespone> getFormatByPage(Pageable pageable);

	public String updateFormat(FormatRequest formatRequest);

	public FormatRespone getFormatById(int id);

	public String insertFormat(FormatInsertRequest formatInsertRequest);

	public String updateStatusFormat(FormatStatusRequest formatStatusRequest);
}
