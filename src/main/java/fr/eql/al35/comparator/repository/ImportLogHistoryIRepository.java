package fr.eql.al35.comparator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.eql.al35.comparator.entity.ImportLogHistory;

@Repository
public interface ImportLogHistoryIRepository extends CrudRepository<ImportLogHistory, Integer>{

}
