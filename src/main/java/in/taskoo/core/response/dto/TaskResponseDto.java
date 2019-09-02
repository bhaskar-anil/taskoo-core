package in.taskoo.core.response.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
class TaskResponseDto{
	private String title;
	
	private String description;
	
	private Integer mode;
	
	private String address;
	
	private LocalDateTime dateTime;

	private Long userId;
	
	private Long estimate;
	
	private Integer estimateType;
}