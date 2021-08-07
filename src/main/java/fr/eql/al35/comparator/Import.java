package fr.eql.al35.comparator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.eql.al35.comparator.entity.Merchant;
import fr.eql.al35.comparator.parser.FeedParser;
import fr.eql.al35.comparator.repository.MerchantIRepository;

@EnableAutoConfiguration
@SpringBootApplication
public class Import implements CommandLineRunner {


	@Autowired
	FeedParser FPS;

	@Autowired
	MerchantIRepository merchantRepository; 

	public static void main(String[] args) {
		SpringApplication.run(Import.class, args);
		System.out.println("Programme lancé");
	}

	@Override
	public void run(String... args) throws Exception {

		List<Merchant> merchantList = new ArrayList<Merchant>(); 
		Merchant rakuten = merchantRepository.findById(6).get();
		Merchant alternate = merchantRepository.findById(9).get();
		merchantList.add(rakuten);
		merchantList.add(alternate);
		for (Merchant merchant : merchantList) {
			System.out.println("꧁∙∙∙∙∙·▫▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ Récupération du fichier ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫▫·∙∙∙∙∙꧂");


			FileUtils.copyURLToFile(
					new URL(merchant.getUrlSource()),
					new File("./src/main/resources/flux/" + merchant.getMerchantName() + "." + merchant.getFileType()));
			
			System.out.println("꧁∙∙∙∙∙·▫▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ Fichier récupéré▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫▫·∙∙∙∙∙꧂");
			if (merchant.getFileType() == "gzip") {
			unGunzipFile("./src/main/resources/flux/" + merchant.getMerchantName().toLowerCase() + "." + merchant.getFileType(), "./src/main/resources/flux/" + merchant.getMerchantName().toLowerCase() + ".csv"); //décompression
			}

			
			System.out.println("꧁∙∙∙∙∙·▫▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ Début de l'import des produits dans la base ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫▫·∙∙∙∙∙꧂");
			FPS.csvToOffer(convertCSVtoInputStream("./src/main/resources/flux/" + merchant.getMerchantName().toLowerCase() + ".csv"), merchant.getId());
			System.out.println("꧁∙∙∙∙∙·▫▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ Fin de l'import des produits dans la base ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫▫·∙∙∙∙∙꧂");
		}

	}


	public static InputStream convertCSVtoInputStream(String path) throws IOException {
		File initialFile = new File(path);
		InputStream targetStream = new FileInputStream(initialFile); //conversion du fichier en InputStream
		return targetStream;
	}

	public void unGunzipFile(String compressedFile, String decompressedFile) {

		byte[] buffer = new byte[1024];

		try {

			FileInputStream fileIn = new FileInputStream(compressedFile);

			GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);

			FileOutputStream fileOutputStream = new FileOutputStream(decompressedFile);

			int bytes_read;

			while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {

				fileOutputStream.write(buffer, 0, bytes_read);
			}

			gZIPInputStream.close();
			fileOutputStream.close();
			
			System.out.println("꧁∙∙∙∙∙·▫▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ Fichier décompressé! ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫▫·∙∙∙∙∙꧂");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
