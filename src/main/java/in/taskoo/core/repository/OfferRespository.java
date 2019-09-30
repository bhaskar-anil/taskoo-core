package in.taskoo.core.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import in.taskoo.core.entity.Offer;

public interface OfferRespository extends CrudRepository<Offer, Long>{

	Optional<Offer> findByIdAndDeleteFlag(Long id,Boolean flag);
}
