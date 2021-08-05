package fr.eql.al35.comparator.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("fr.eql.al35.entity")
@Entity(name = "merchant")
public class Merchant implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private String merchantName; 
	private String imageURL;
	private String source; 
	private Double cpc;
	
	@Column(length = 500)
	private String urlSource; 
	private String fileType; 
	
	private String configEan;
	private String configProductName;
	private String configDescription;
	private String configPrice;
	private String configUrl;
	private Integer configRowSize;
	private char configDelimiter;
	
	public Merchant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Merchant(Integer id, String merchantName, String imageURL, String source, Double cpc, String urlSource,
			String fileType, String configEan, String configProductName, String configDescription, String configPrice,
			String configUrl, Integer configRowSize, char configDelimiter) {
		super();
		this.id = id;
		this.merchantName = merchantName;
		this.imageURL = imageURL;
		this.source = source;
		this.cpc = cpc;
		this.urlSource = urlSource;
		this.fileType = fileType;
		this.configEan = configEan;
		this.configProductName = configProductName;
		this.configDescription = configDescription;
		this.configPrice = configPrice;
		this.configUrl = configUrl;
		this.configRowSize = configRowSize;
		this.configDelimiter = configDelimiter;
	}

	@Override
	public int hashCode() {
		return Objects.hash(configDelimiter, configDescription, configEan, configPrice, configProductName,
				configRowSize, configUrl, cpc, fileType, id, imageURL, merchantName, source, urlSource);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Merchant other = (Merchant) obj;
		return configDelimiter == other.configDelimiter && Objects.equals(configDescription, other.configDescription)
				&& Objects.equals(configEan, other.configEan) && Objects.equals(configPrice, other.configPrice)
				&& Objects.equals(configProductName, other.configProductName)
				&& Objects.equals(configRowSize, other.configRowSize) && Objects.equals(configUrl, other.configUrl)
				&& Objects.equals(cpc, other.cpc) && Objects.equals(fileType, other.fileType)
				&& Objects.equals(id, other.id) && Objects.equals(imageURL, other.imageURL)
				&& Objects.equals(merchantName, other.merchantName) && Objects.equals(source, other.source)
				&& Objects.equals(urlSource, other.urlSource);
	}

	@Override
	public String toString() {
		return "Merchant [id=" + id + ", merchantName=" + merchantName + ", imageURL=" + imageURL + ", source=" + source
				+ ", cpc=" + cpc + ", urlSource=" + urlSource + ", fileType=" + fileType + ", configEan=" + configEan
				+ ", configProductName=" + configProductName + ", configDescription=" + configDescription
				+ ", configPrice=" + configPrice + ", configUrl=" + configUrl + ", configRowSize=" + configRowSize
				+ ", configDelimiter=" + configDelimiter + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Double getCpc() {
		return cpc;
	}

	public void setCpc(Double cpc) {
		this.cpc = cpc;
	}

	public String getUrlSource() {
		return urlSource;
	}

	public void setUrlSource(String urlSource) {
		this.urlSource = urlSource;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getConfigEan() {
		return configEan;
	}

	public void setConfigEan(String configEan) {
		this.configEan = configEan;
	}

	public String getConfigProductName() {
		return configProductName;
	}

	public void setConfigProductName(String configProductName) {
		this.configProductName = configProductName;
	}

	public String getConfigDescription() {
		return configDescription;
	}

	public void setConfigDescription(String configDescription) {
		this.configDescription = configDescription;
	}

	public String getConfigPrice() {
		return configPrice;
	}

	public void setConfigPrice(String configPrice) {
		this.configPrice = configPrice;
	}

	public String getConfigUrl() {
		return configUrl;
	}

	public void setConfigUrl(String configUrl) {
		this.configUrl = configUrl;
	}

	public Integer getConfigRowSize() {
		return configRowSize;
	}

	public void setConfigRowSize(Integer configRowSize) {
		this.configRowSize = configRowSize;
	}

	public char getConfigDelimiter() {
		return configDelimiter;
	}

	public void setConfigDelimiter(char configDelimiter) {
		this.configDelimiter = configDelimiter;
	}

	

}