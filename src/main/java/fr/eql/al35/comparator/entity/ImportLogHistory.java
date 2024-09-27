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
public class ImportLogHistory {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer Id; 
	private LocalDateTime importTime;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch= FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant; 
	private Integer created; 
	private Integer updated; 
	private Integer rejected; 
	private Integer deleted;
	
	public ImportLogHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	* build  a log from an import. 
	*
	* @param id
	* @param importtime the exact time the database was imported
	* @param merchant the catalog which was imported 
	* @param created the number of row that were created
	* @param updated the number of row that were updated
	* @param rejected the number of row that were rejected
	* @param deleted the number of row that were deleted
	* @return nothing
	*/
	public ImportLogHistory(Integer id, LocalDateTime importTime, Merchant merchant, Integer created, Integer updated,
			Integer rejected, Integer deleted) {
		super();
		Id = id;
		this.importTime = importTime;
		this.merchant = merchant;
		this.created = created;
		this.updated = updated;
		this.rejected = rejected;
		this.deleted = deleted;
	}
	
	/**
	Same constructor as above without the ID for AutoIncrement in SQL Database
	 */
	public ImportLogHistory(LocalDateTime importTime, Merchant merchant, Integer created, Integer updated,
			Integer rejected, Integer deleted) {
		super();
		this.importTime = importTime;
		this.merchant = merchant;
		this.created = created;
		this.updated = updated;
		this.rejected = rejected;
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "ImportLogHistory [Id=" + Id + ", importTime=" + importTime + ", merchant=" + merchant + ", created="
				+ created + ", updated=" + updated + ", rejected=" + rejected + ", deleted=" + deleted + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, created, deleted, importTime, merchant, rejected, updated);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImportLogHistory other = (ImportLogHistory) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(created, other.created)
				&& Objects.equals(deleted, other.deleted) && Objects.equals(importTime, other.importTime)
				&& Objects.equals(merchant, other.merchant) && Objects.equals(rejected, other.rejected)
				&& Objects.equals(updated, other.updated);
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public LocalDateTime getImportTime() {
		return importTime;
	}

	public void setImportTime(LocalDateTime importTime) {
		this.importTime = importTime;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}

	public Integer getUpdated() {
		return updated;
	}

	public void setUpdated(Integer updated) {
		this.updated = updated;
	}

	public Integer getRejected() {
		return rejected;
	}

	public void setRejected(Integer rejected) {
		this.rejected = rejected;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	} 
	
	
	
	
}
