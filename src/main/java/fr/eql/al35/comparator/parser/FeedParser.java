package fr.eql.al35.comparator.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.comparator.entity.Merchant;
import fr.eql.al35.comparator.entity.Offer;
import fr.eql.al35.comparator.repository.MerchantIRepository;
import fr.eql.al35.comparator.service.OfferService;


@Service
public class FeedParser {


	@Autowired
	private OfferService offerService;

	@Autowired
	private MerchantIRepository merchantIRepository;
	
	static Merchant merchant; 
	static Offer offer = new Offer();
	static int created = 0; 
	static int rejected = 0; 
	static int updated = 0; 
	
	public String TYPE = "text/csv";
	public void csvToOffer(InputStream is, Integer source) {
		
		//récupération de l'ID du  marchand en fonction de la source
		merchant = merchantIRepository.findById(source).get();
		String configEan = merchant.getConfigEan();
		String configProductName = merchant.getConfigProductName(); 
		String configDescription = merchant.getConfigDescription(); 
		String configPrice = merchant.getConfigPrice(); 
		String configUrl = merchant.getConfigUrl();
		Integer configRowSize = merchant.getConfigRowSize();
		char delimiter = merchant.getConfigDelimiter(); 
		
		long startTime = System.nanoTime(); //flag de début pour le calcul du temps d'éxécution.
		
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withDelimiter(delimiter)
						.withHeader(configEan, configProductName, configDescription,configPrice	, configUrl) //déclaration des headers à récupérer
						.withFirstRecordAsHeader() //saut de la première ligne 
						.withIgnoreHeaderCase()
						.withTrim()
						.withIgnoreEmptyLines()
						.withQuote(null));) //pour les quotes insérées par erreur dans les ligne 
		{
			
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord csvRecord : csvRecords) {
				
				String cleanPrice = "";
				
				try {
				String price = csvRecord.get(configPrice);
				cleanPrice = price.replace("EUR","");
				} catch (Exception e) {
					System.out.println(e.toString());
					continue;
				}
				System.out.println("rowsize : " + csvRecord.size());
				if (csvRecord.size() != configRowSize ) {  //on ignore les lignes mal formatées
					rejected ++;
					continue;

				} else if (csvRecord.get(configEan).equalsIgnoreCase("")) { //on ignore les lignes sans EAN
					rejected ++;
					continue;

				} else if (cleanPrice.matches(".*[a-z].*")) { //on ignore les lignes dont les contenus génèrent des erreurs de type (double/string)
					rejected ++;
					continue;
				} 
				
				
				
				Offer offer = new Offer(
						csvRecord.get(configEan) + merchant.getSource() + merchant.getMerchantName(),
						csvRecord.get(configEan),
						csvRecord.get(configProductName),
						csvRecord.get(configDescription),
						csvRecord.get(configUrl),
						Double.parseDouble(cleanPrice),
						merchant);
					offerService.insertOnDuplicateKey(offer);
					created++;
				System.out.println("Traitement de la ligne " + csvRecord.getRecordNumber());
			}
			
			System.out.println("Rapport du flux " + merchant.getMerchantName());
			System.out.println("Nombre de lignes insérées dans la base de données : " + created);
			System.out.println("Nombre de lignes mise à jour dans la base de données : " + updated);
			System.out.println("Nombre de lignes rejetées : " + rejected);
			long endTime = System.nanoTime(); //flag de fin pour le calcul du temps d'éxécution.
			long duration = (endTime - startTime);
			System.out.println("Durée de l'import :" + (duration/1000000000)/60 + " minutes");
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
	
	


}
