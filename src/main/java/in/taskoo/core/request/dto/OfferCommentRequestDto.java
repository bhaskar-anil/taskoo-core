package in.taskoo.core.request.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OfferCommentRequestDto {
	
	@NotNull(message="comment can't be null")
	private String comment;
	
	@NotNull(message="commenterId can't be null")
	private String commenterId;
}
