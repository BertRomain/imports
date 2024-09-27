package fr.eql.al35.comparator.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.comparator.entity.ImportLogHistory;
import fr.eql.al35.comparator.entity.Merchant;
import fr.eql.al35.comparator.entity.Offer;
import fr.eql.al35.comparator.repository.MerchantIRepository;


@Service
public class FeedParserService {


	@Autowired
	private OfferService offerService;

	@Autowired
	private LogService logService;

	@Autowired
	private MerchantIRepository merchantIRepository;

	static Merchant merchant; 
	static Offer offer = new Offer();
	static Integer rejected = 0; 
	static Integer created = 0;
	static Integer updated = 0;


	public String TYPE = "text/csv";

	public void csvToOffer(InputStream is, Integer source) {
		
		
		//reset counter 
		resetCounters();
		
		//récupération de l'ID du  marchand en fonction de la source
		merchant = merchantIRepository.findById(source).get();
		final String configEan = merchant.getConfigEan();
		final String configProductName = merchant.getConfigProductName(); 
		final String configDescription = merchant.getConfigDescription(); 
		final String configPrice = merchant.getConfigPrice(); 
		final String configUrl = merchant.getConfigUrl();
		final Integer configRowSize = merchant.getConfigRowSize();
		final char configDelimiter = merchant.getConfigDelimiter(); 

		final long startTime = System.nanoTime(); //flag de début pour le calcul du temps d'éxécution.

		ImportLogHistory importLog = new ImportLogHistory(LocalDateTime.now(), merchant, 0 , 0, 0 ,0);


		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withDelimiter(configDelimiter)
						.withHeader(configEan, configProductName, configDescription,configPrice	, configUrl) //déclaration des headers à récupérer
						.withFirstRecordAsHeader() //saut de la première ligne 
						.withIgnoreHeaderCase()
						.withTrim()
						.withIgnoreEmptyLines()
						.withQuote(null));) //pour les quotes insérées par erreur dans les lignes
						
		{
			final Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord csvRecord : csvRecords) {
				System.out.print('\r');
				System.out.print("Import du catalogue " + merchant.getMerchantName() + " ligne n°" + csvRecord.getRecordNumber());
				String cleanPrice = "";

				try {
					String price = csvRecord.get(configPrice);
					cleanPrice = price.replace("EUR","");
				} catch (Exception e) {
					logService.ErrorLogging(csvRecord, merchant, "replace");
					rejected++;				
					continue;
				}

				
				if (csvRecord.size() != configRowSize ) {  //on ignore les lignes mal formatées
					importLog.setRejected(importLog.getRejected() +1);
					logService.ErrorLogging(csvRecord, merchant, "size");
					rejected++;
					continue;

				} else if (csvRecord.get(configEan).equalsIgnoreCase("")) { //on ignore les lignes sans EAN
					importLog.setRejected(importLog.getRejected() +1);
					logService.ErrorLogging(csvRecord, merchant, "ean");
					rejected++;
					continue;

				} else if (cleanPrice.matches(".*[a-z].*")) { //on ignore les lignes dont les contenus génèrent des erreurs de type (double/string)
					importLog.setRejected(importLog.getRejected() +1);
					logService.ErrorLogging(csvRecord, merchant, "typeError");
					rejected++;
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
				created++;
				offerService.insertOnDuplicateKey(offer);
			}


			System.out.println("Rapport du flux " + merchant.getMerchantName());
			System.out.println("Nombre de lignes insérées dans la base de données : " + created);
			System.out.println("Nombre de lignes mise à jour dans la base de données : " + updated);
			System.out.println("Nombre de lignes rejetées : " + rejected);
			long endTime = System.nanoTime(); //flag de fin pour le calcul du temps d'éxécution.
			long duration = (endTime - startTime);
			System.out.println("Durée de l'import : " + (duration/1000000000)/60 + " minutes");
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
		
		
	}

	public void resetCounters()
    {
		rejected = 0; 
		created = 0;
		updated = 0;
    }


}
