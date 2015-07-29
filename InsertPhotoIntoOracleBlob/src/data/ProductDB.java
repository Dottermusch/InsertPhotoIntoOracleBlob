package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.Product;

public class ProductDB 
{
	public static int insertProduct(Product product)
	{
		int count = 0;
		Connection con = null;
		
		try
		{
			// step 1 Load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// step 2 create the driver connection object
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "password");
			
			// step 3 - crate the query
			String query = "INSERT INTO TESTUSER1.DEMO_PRODUCT_INFO " +
					   "(PRODUCT_NAME, PRODUCT_DESCRIPTION, CATEGORY, PRODUCT_AVAIL, LIST_PRICE, PRODUCT_IMAGE, MIMETYPE, FILENAME ) " +
					   "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			// step 4 create prepared statement adding the query
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getProductDescription());
			pstmt.setString(3, product.getCategory());
			pstmt.setString(4, product.getAvailability());
			pstmt.setBigDecimal(5, product.getListPrice());
			pstmt.setBinaryStream(6,product.getImage(),product.getImage().available()); 
			pstmt.setString(7, product.getMimeType());
			pstmt.setString(8, product.getFileName());
			
			count = pstmt.executeUpdate();
			con.close();
			
			return count;
			
		} catch (Exception e)
		{
			System.out.println(e);
			return count;
		}
	}
	
	public static FileInputStream getFileToBytes(String filename)
	{
		/*
    	 * 1. How to convert an image file to  byte array?
    	 */
 
        File file = new File(filename);
        FileInputStream fis = null;
        
        try
        {
        	fis = new FileInputStream(file);
        //  create FileInputStream which obtains input bytes from a file in a file system
        //  FileInputStream is meant for reading streams of raw bytes such as image data. 
        //	For reading streams of characters, consider using FileReader.
        }
        catch (FileNotFoundException fnfe)
        {
        	fis = null;
        }
        return fis;
	}
	
	public static int getBytesToFile(byte[] image, String fileNamePath)
	{
		try
		{
			// String CanonicalPath = new File(".").getCanonicalPath();
			OutputStream out = new FileOutputStream(fileNamePath);
			// OutputStream out = new FileOutputStream("./images/" + product.getFilename());
			out.write(image);
			out.close();
		}
		catch (Exception e)
		{
			System.out.println("A problem occurred while attempting to create the image file: " + e);
			return 0;
		}
	
	return image.length;
	}
	
}
