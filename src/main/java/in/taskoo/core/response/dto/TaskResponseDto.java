package in.taskoo.core.response.dto;

import java.time.LocalDateTime;

import in.taskoo.core.constant.EstimateType;
import in.taskoo.core.constant.TaskStatus;
import in.taskoo.core.constant.TaskType;
import lombok.Data;

@Data
public class TaskResponseDto{
	private String title;
	
	private String description;
	
	private TaskType taskType;
	
	private String location;
	
	private LocalDateTime taskDateTime;
	
	private LocalDateTime taskDueDateTime;
	
	private Long seekerId;
	
	private Long estimateAmount;
	
	private EstimateType estimateType;
	
	private TaskStatus taskStatus;
}