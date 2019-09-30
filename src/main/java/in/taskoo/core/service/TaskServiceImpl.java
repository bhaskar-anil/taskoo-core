package in.taskoo.core.service;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import in.taskoo.core.constant.TaskStatus;
import in.taskoo.core.entity.Task;
import in.taskoo.core.error.message.ApplicationErrorMessages;
import in.taskoo.core.exception.NoDataFoundException;
import in.taskoo.core.repository.TaskRespository;
import in.taskoo.core.request.dto.TaskCreateRequestDto;
import in.taskoo.core.request.dto.TaskUpdateRequestDto;
import in.taskoo.core.response.dto.TaskResponseDto;

@Service
public class TaskServiceImpl implements TaskService {

	@Resource 
	private TaskRespository taskRespository;
	
	@Resource
	private Mapper mapper;
	
	@Override
	public Task create(TaskCreateRequestDto taskCreateRequestDto) {
		Task task = mapper.map(taskCreateRequestDto, Task.class);
		task.setTaskStatus(TaskStatus.INITIATED);
		return taskRespository.save(task);
	}

	@Override
	public TaskResponseDto get(Long taskId) {
		Task task = taskRespository.findByIdAndDeleteFlag(taskId,Boolean.FALSE).orElseThrow(()->NoDataFoundException.getException(ApplicationErrorMessages.NO_DATA_FOUND));
		return mapper.map(task, TaskResponseDto.class);
	}

	@Override
	public Task delete(Long taskId) {
		Task task = taskRespository.findByIdAndDeleteFlag(taskId,Boolean.FALSE).orElseThrow(()->NoDataFoundException.getException(ApplicationErrorMessages.NO_DATA_FOUND));
		task.setDeleteFlag(Boolean.TRUE);
		return taskRespository.save(task);
	}

	@Override
	public Task update(TaskUpdateRequestDto taskUpdateRequestDto, Long taskId) {
		Task task = taskRespository.findByIdAndDeleteFlag(taskId,Boolean.FALSE).orElseThrow(()->NoDataFoundException.getException(ApplicationErrorMessages.NO_DATA_FOUND));
		mapper.map(taskUpdateRequestDto, task);
		return taskRespository.save(task);
	}

}
