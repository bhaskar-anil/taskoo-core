package in.taskoo.core.request.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class OfferCreateRequestDto {
	
	@NotNull(message="taskerId can't be null")
	private Long  taskerId;
	
	@NotNull(message="taskId can't be null")
	private Long  taskId;
	
	@NotNull(message="note can't be null")
	@Size(max=2000, message="note max length 2000")
	private String note;
	
	private Long offerAmount;
	
}
