package dto.request;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import dto.reponse.ReviewRespone;
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
public class ReviewRequest {
	
	@NotNull(message="Id product cannot null")
	private int productId;
	
	@NotBlank(message="Content cannot be empty")
	private String content;
	
	@NotNull(message="Rating start cannot be null")
	private int rating;
	
}
