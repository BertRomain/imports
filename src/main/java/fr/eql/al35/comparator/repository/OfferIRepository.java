package fr.eql.al35.comparator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.eql.al35.comparator.entity.Offer;

@Repository
public interface OfferIRepository extends CrudRepository<Offer, String> {
	
	@Query("Select o FROM offer o WHERE o.ean = :ean")
	List<Offer> findByEan(@Param("ean") String ean);
	
}
