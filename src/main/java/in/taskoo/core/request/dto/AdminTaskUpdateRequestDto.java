package in.taskoo.core.request.dto;

import in.taskoo.core.constant.TaskStatus;
import lombok.Data;

@Data
public class AdminTaskUpdateRequestDto {
	TaskStatus taskStatus;
	Long categoryId;
}
