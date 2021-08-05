package fr.eql.al35.comparator.iservice;

import java.util.List;

import fr.eql.al35.comparator.entity.Merchant;

public interface MerchantIService {

	public List<Merchant> findAll();
	public Merchant findById(); 
}
