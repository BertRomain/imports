package fr.eql.al35.comparator.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "offer")
public class Offer implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	private String id; 
	private String ean;
	private String productName;
	@Column(length = 15000)
	private String description;
	
	@Column(length = 500)
	private String url;
	private Double price;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch= FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;

	private LocalDate createDate;
	private LocalDate modifyDate;
	
		
	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Offer(String ean, String productName, String description, String url, Double price, Merchant merchant,
			LocalDate modifyDate) {
		super();
		this.ean = ean;
		this.productName = productName;
		this.description = description;
		this.url = url;
		this.price = price;
		this.merchant = merchant;
		this.modifyDate = modifyDate;
	}


	public Offer(String id, String ean, String productName, String description, String url, Double price, Merchant merchant) {
		super();
		this.id = id; 
		this.ean = ean;
		this.productName = productName;
		this.description = description;
		this.url = url;
		this.price = price;
		this.merchant = merchant;
	}


	@Override
	public String toString() {
		return "Offer [id=" + id + ", ean=" + ean + ", productName=" + productName + ", description=" + description
				+ ", url=" + url + ", price=" + price + ", merchant=" + merchant + "]";
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public LocalDate getCreateDate() {
		return createDate;
	}


	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}


	public LocalDate getModifyDate() {
		return modifyDate;
	}


	public void setModifyDate(LocalDate modifyDate) {
		this.modifyDate = modifyDate;
	}


	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((ean == null) ? 0 : ean.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (ean == null) {
			if (other.ean != null)
				return false;
		} else if (!ean.equals(other.ean))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
}