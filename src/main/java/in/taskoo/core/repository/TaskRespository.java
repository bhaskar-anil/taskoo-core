package in.taskoo.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.taskoo.core.entity.Task;

public interface TaskRespository extends CrudRepository<Task, Long>{
	
}
