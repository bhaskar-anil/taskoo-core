package in.taskoo.core.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import in.taskoo.core.entity.Offer;
import in.taskoo.core.entity.Task;

public interface OfferRespository extends CrudRepository<Offer, Long>{

	Optional<Offer> findByIdAndDeleteFlag(Long id,Boolean deleteFlag);

	Page<Offer> findByTaskAndDeleteFlag(Task task,Boolean deleteFlag, Pageable pageable);
}
