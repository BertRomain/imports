package fr.eql.al35.comparator.service;

import java.time.LocalDateTime;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.comparator.entity.ErrorLog;
import fr.eql.al35.comparator.entity.ImportLogHistory;
import fr.eql.al35.comparator.entity.Merchant;
import fr.eql.al35.comparator.repository.ErrorLogIRepository;
import fr.eql.al35.comparator.repository.ImportLogHistoryIRepository;


@Service
public class LogService {
	
	@Autowired
	private ErrorLogIRepository errorLogIRepository; 
	
	@Autowired
	private ImportLogHistoryIRepository importLogHistoryIRepository;
	
	public void ErrorLogging(CSVRecord record, Merchant merchant, String errorType) {
	ErrorLog errorLog = new ErrorLog(LocalDateTime.now(), merchant, record.toString(), errorType);
	System.out.println(errorLog.toString());
	errorLogIRepository.save(errorLog);
	}
	
	
	public void ImportLogging (Integer created, Integer updated, Integer rejected, Integer deleted, Merchant merchant) {
		ImportLogHistory importLog = new ImportLogHistory(null, LocalDateTime.now(), merchant, created, updated, rejected, deleted);
	importLogHistoryIRepository.save(importLog);
	}
	
}
