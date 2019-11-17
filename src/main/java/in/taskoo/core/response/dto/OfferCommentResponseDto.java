package in.taskoo.core.response.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OfferCommentResponseDto {
	
	private LocalDateTime commentDateTime;
	private String comment;
	private Long commenterId;
	
}
