package fr.eql.al35.comparator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.eql.al35.comparator.entity.Merchant;

@Repository
public interface MerchantIRepository extends CrudRepository<Merchant, Integer> {

}
