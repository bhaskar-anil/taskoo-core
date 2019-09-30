package in.taskoo.core.request.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TaskUpdateRequestDto {

		@Size(min=5,max=100, message="description length should be [5-100]")
		private String title;
		
		@Size(min=50,max=1000, message="description length should be [50-1000]")
		private String description;
		
		@Min(value=0,message="mode should range from [0-2]")
		@Max(value=2,message="mode should range from [0-2]")
		private Integer mode;
		
		@Size(min=1,max=255, message="description length should be [1-255]")
		private String address;
		
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		private LocalDateTime dateTime;

		@NotNull(message = "estimate can't be null")
		private Long estimate;
		
		@Min(value=0,message="estimateType should range from [0-1]")
		@Max(value=1,message="estimateType should range from [0-1]")
		private Integer estimateType;
		
		@Min(value=0,message="taskStatus should range from [0-1]")
		@Max(value=1,message="taskStatus should range from [0-1]")
		private Integer taskStatus;
	
}
