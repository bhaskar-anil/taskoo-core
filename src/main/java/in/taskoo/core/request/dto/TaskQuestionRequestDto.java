package in.taskoo.core.request.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TaskQuestionRequestDto {
	
	@NotNull(message="statement can't be null")
	private String statement;
	
	@NotNull(message="userId can't be null")
	private Long userId;
	
	private Long parentId;
}
