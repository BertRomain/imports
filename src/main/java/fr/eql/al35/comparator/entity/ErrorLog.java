package fr.eql.al35.comparator.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ErrorLog {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;  
	private LocalDateTime importTime; 
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch= FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;
	private String csvRecord;
	private String errorType;

	public ErrorLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	* build  an errorLog object that can then be persisted in the database. 
	*
	* @param id
	* @param importtime the exact time the offer was imported
	* @param merchant the location of the image, relative to the url argument
	* @param csvRecord is the row which generates an error
	* @param errorType is the reason that the row generates and error
	* @return nothing
	*/
	public ErrorLog(Integer id, LocalDateTime importtime, Merchant merchant, String csvRecord, String errorType) {
		super();
		this.id = id; 
		this.importTime = importtime;
		this.merchant = merchant;
		this.csvRecord = csvRecord;
		this.errorType = errorType;
	}
	
	
	/**
	* build  an errorLog object that can then be persisted in the database without the ID. 
	*
	* @param id
	* @param importtime the exact time the offer was imported
	* @param merchant the location of the image, relative to the url argument
	* @param csvRecord is the row which generates an error
	* @param errorType is the reason that the row generates and error
	* @return nothing
	*/
	public ErrorLog(LocalDateTime importtime, Merchant merchant, String csvRecord, String errorType) {
		super();
		this.importTime = importtime;
		this.merchant = merchant;
		this.csvRecord = csvRecord;
		this.errorType = errorType;
	}

	@Override
	public String toString() {
		return "ErrorLog [id=" + id + ", importtime=" + importTime + ", merchant=" + merchant + ", csvRecord="
				+ csvRecord + ", errorType=" + errorType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(csvRecord, errorType, id, importTime, merchant);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorLog other = (ErrorLog) obj;
		return Objects.equals(csvRecord, other.csvRecord) && Objects.equals(errorType, other.errorType)
				&& Objects.equals(id, other.id) && Objects.equals(importTime, other.importTime)
				&& Objects.equals(merchant, other.merchant);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getImporttime() {
		return importTime;
	}

	public void setImporttime(LocalDateTime importtime) {
		this.importTime = importtime;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getCsvRecord() {
		return csvRecord;
	}

	public void setCsvRecord(String csvRecord) {
		this.csvRecord = csvRecord;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

}