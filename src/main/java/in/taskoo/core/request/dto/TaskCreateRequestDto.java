package in.taskoo.core.request.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.taskoo.core.constant.EstimateType;
import in.taskoo.core.constant.TaskType;
import lombok.Data;

@Data
public class TaskCreateRequestDto {
    @JsonIgnore
    private Long id;

		@NotNull(message = "title can't be null")
		@Size(min=20,max=150, message="title length should be [20-150]")
		private String title;
		
		@NotNull(message = "description can't be null")
		@Size(max=2000, message="description max length 2000")
		private String description;
		
		@NotNull(message = "taskType can't be null")
		private TaskType taskType;
		
		@NotNull(message = "location can't be blank")
		@Size(min=1,max=255, message="location length should be [1-255]")
		private String location;
		
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@NotNull(message="taskDueDateTime can't be null")
		private LocalDateTime taskDueDateTime;

		@NotNull(message = "seekerId can't be null")
		private Long seekerId;
		
		@NotNull(message = "estimateAmount can't be null")
		private Long estimateAmount;
		
		@NotNull(message = "estimateType can't be blank")
		private EstimateType estimateType;
		
		@NotNull(message = "categoryId can't be blank")
		private Long categoryId;
}
