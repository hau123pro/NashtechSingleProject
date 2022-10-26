package dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import dto.reponse.CategoryRespone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.constant.Status;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryStatusRequest {
	
	@NotNull(message="id cannot be null")
	private int id;
	
	@NotBlank(message="Status cannot be empty")
	private Status status;
}
