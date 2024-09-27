package fr.eql.al35.comparator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.eql.al35.comparator.entity.ErrorLog;

@Repository
public interface ErrorLogIRepository extends CrudRepository<ErrorLog, Integer>{

}
