package dto.reponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductRespone {
	
	private int Id;
	
	private String productName;
	
	private String description;
	
	private String imgUrl;
	
	private ProductAuthorResponse authorResponse;
	
//	private List<ReviewRespone> reviewRespones;
	
	private List<FormatRespone> formatRespones;
	
	private List<CategoryRespone> categoryRespones;
	
}
