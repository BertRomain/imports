package fr.eql.al35.comparator.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.eql.al35.comparator.entity.Offer;
import fr.eql.al35.comparator.iservice.OfferIService;


@Transactional
@Service
public class OfferService implements OfferIService{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Offer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Offer> findByEan(Offer offer) {
	return null;
	}


	@Override
	public Offer findByEanAndMerchant(Offer offer) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void mergeOffer(Offer offer) {
	Query query = em.createQuery("SELECT o FROM offer o WHERE o.ean = :paramean AND o.merchant = :parammerchant");
	query.setParameter("paramean", offer.getEan());
	query.setParameter("parammerchant", offer.getMerchant());
	Offer retrievedOffer = (Offer) query.getSingleResult();
	LocalDate localDate = LocalDate.now();
	retrievedOffer.setPrice(offer.getPrice());
	retrievedOffer.setDescription(offer.getDescription());
	retrievedOffer.setProductName(offer.getProductName());
	retrievedOffer.setUrl(offer.getUrl());
	retrievedOffer.setModifyDate(localDate);
	em.merge(retrievedOffer);
	}


	@Override
	public Offer existByEan(Offer offer) {
		Query query = em.createQuery("SELECT o FROM offer o WHERE o.ean = :paramean AND o.merchant = :parammerchant");
		query.setParameter("paramean", offer.getEan());
		query.setParameter("parammerchant", offer.getMerchant());
		try {
			Offer retrievedOffer = (Offer) query.getSingleResult();
			return retrievedOffer;
		} catch (NoResultException nre) {
		}
		return null;
	}


	@Override
	public void insertOnDuplicateKey(Offer offer) {
		Query query = em.createNativeQuery(
"INSERT INTO offer (id, ean, product_name, description, url, price, create_date, modify_date, merchant_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, NULL, ?8) " +
		        "ON DUPLICATE KEY UPDATE " +
		            "product_name = ?3, " +
		            "description = ?4, " +
		            "url = ?5, " +
		            "price = ?6, " +
		            "modify_date = ?7 ;"
		            );
		query.setParameter(1, offer.getId());
		query.setParameter(2, offer.getEan());
		query.setParameter(3, offer.getProductName());
		query.setParameter(4, offer.getDescription());
		query.setParameter(5, offer.getUrl());
		query.setParameter(6, offer.getPrice());
		query.setParameter(7, java.time.LocalDate.now());
		query.setParameter(8, offer.getMerchant().getId());
		query.executeUpdate();
			
	}

}
