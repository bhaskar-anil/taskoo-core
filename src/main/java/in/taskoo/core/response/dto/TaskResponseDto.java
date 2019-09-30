package in.taskoo.core.response.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskResponseDto{
	private String title;
	
	private String description;
	
	private Integer mode;
	
	private String address;
	
	private LocalDateTime dateTime;

	private Long seekerId;
	
	private Long estimate;
	
	private Integer estimateType;
	
	private Integer taskStatus;
}