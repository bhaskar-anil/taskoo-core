package in.taskoo.core.response.dto;

import java.time.LocalDateTime;
import java.util.List;

import in.taskoo.core.constant.OfferStatus;
import lombok.Data;

@Data
public class OfferResponseDto {
	
	private Long  taskerId;
	
	private LocalDateTime offerDateTime;
	
	private List<OfferCommentResponseDto> comments;
	
	private OfferStatus offerStatus;
	
	private String note;
	
}
