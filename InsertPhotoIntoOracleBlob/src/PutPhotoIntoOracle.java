import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Logger;

import data.ProductDB;
import model.Product;


public class PutPhotoIntoOracle {

	public static void main(String[] args) 
	{
		Product product = new Product();
		String fileToLoad = "C:\\Temp\\rick_sized.jpg";
		product.setProductName("Photo");
		product.setFileName(fileToLoad);
		product.setCategory("Other");
		product.setAvailability("Y");
		product.setLastUpdate(new Date());
		product.setListPrice(new BigDecimal("5.95"));
		product.setMimeType("image/jpeg");
		product.setProductDescription("A really good photo");
		product.setImage(ProductDB.getFileToBytes(fileToLoad));
		
		int count = ProductDB.insertProduct(product);
		
		System.out.println("The number of records inserted was: " + count);
	}

}
