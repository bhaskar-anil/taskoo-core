package in.taskoo.core.response.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskQuestionResponseDto {
	
	private String statement;
	private Long userId;
	private Long parentId;
	private LocalDateTime createDateTime;
}
