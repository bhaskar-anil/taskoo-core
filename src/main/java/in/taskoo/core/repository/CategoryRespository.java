package in.taskoo.core.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import in.taskoo.core.entity.Category;

public interface CategoryRespository extends CrudRepository<Category, Long>{

	Optional<Category> findByIdAndDeleteFlag(Long id,Boolean flag);
}
