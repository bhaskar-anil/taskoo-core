package in.taskoo.core.request.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OfferUpdateRequestDto {
	@NotNull(message="taskerId can't be null")
	private Long  taskerId;
	
	@NotNull(message = "offerStatus can't be null")
	@Min(value=0,message="offerStatus should range from [0-1]")
	@Max(value=4,message="offerStatus should range from [0-1]")
	private Integer offerStatus;
}
