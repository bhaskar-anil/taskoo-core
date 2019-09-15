package in.taskoo.core.service;

import in.taskoo.core.entity.Task;
import in.taskoo.core.request.dto.TaskCreateRequestDto;
import in.taskoo.core.response.dto.TaskResponseDto;

public interface TaskService {
	public Task create(TaskCreateRequestDto taskCreateRequestDto);
	public TaskResponseDto get(Long taskId);
	public Task delete(Long taskId);
}
