package in.taskoo.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import in.taskoo.core.entity.Task;
import in.taskoo.core.entity.TaskQuestion;

public interface TaskQuestionRepository extends CrudRepository<TaskQuestion, Long>{

	Optional<TaskQuestion> findByIdAndDeleteFlag(Long id,Boolean deleteFlag);

	List<TaskQuestion> findByTaskAndDeleteFlagOrderByIdDesc(Task task,Boolean deleteFlag);
}
