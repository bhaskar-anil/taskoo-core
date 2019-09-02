package in.taskoo.core.service;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import in.taskoo.core.entity.Task;
import in.taskoo.core.repository.TaskRespository;
import in.taskoo.core.request.dto.TaskCreateRequestDto;

@Service
public class TaskServiceImpl implements TaskService {

	@Resource 
	private TaskRespository taskRespository;
	
	@Resource
	private Mapper mapper;
	
	@Override
	public Task create(TaskCreateRequestDto taskCreateRequestDto) {
		Task task = mapper.map(taskCreateRequestDto, Task.class);
		return taskRespository.save(task);
	}

	@Override
	public Task get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
