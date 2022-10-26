package dto.request;

import java.util.List;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductStatusRequest {
	@NotNull(message="Product ID cannot null")
	public int id;
	
	@NotNull(message="Status cannot null")
	@Min(value=0,message="Status cannot smaller than 0")
	@Max(value=1,message="Status cannot bigger than 1")
	private int status;
}
