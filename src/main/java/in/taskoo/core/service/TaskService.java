package in.taskoo.core.service;

import in.taskoo.core.entity.Task;
import in.taskoo.core.request.dto.TaskCreateRequestDto;

public interface TaskService {
	public Task create(TaskCreateRequestDto taskCreateRequestDto);
	public Task get(Long id);
	public Task delete(Long id);
}
