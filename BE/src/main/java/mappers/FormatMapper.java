package mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.reponse.FormatRespone;
import entity.Format;
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
	
}
