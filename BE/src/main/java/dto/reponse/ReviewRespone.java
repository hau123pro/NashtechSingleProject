package dto.reponse;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRespone {
	
	private String userName;
	
	private String content;
	
	private int rating;
	
	private Date dateCreate;
}
