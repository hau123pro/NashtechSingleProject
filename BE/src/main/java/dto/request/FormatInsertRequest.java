package dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FormatInsertRequest {
	
	@NotBlank(message="Name category cannot be empty")
	private String formatName;
	
	private String description;
	
	@NotNull(message="Bonus Price category cannot be null")
	private int bonusPrice;
}
