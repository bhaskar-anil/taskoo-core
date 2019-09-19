package in.taskoo.core.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import in.taskoo.core.entity.Task;

public interface TaskRespository extends CrudRepository<Task, Long>{

	Optional<Task> findByIdAndDeleteFlag(Long id,Boolean flag);
}
