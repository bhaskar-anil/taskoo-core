package in.taskoo.core.repository;

import org.springframework.data.repository.CrudRepository;

import in.taskoo.core.entity.TaskHistory;

public interface TaskHistoryRespository extends CrudRepository<TaskHistory, Long>{

}
