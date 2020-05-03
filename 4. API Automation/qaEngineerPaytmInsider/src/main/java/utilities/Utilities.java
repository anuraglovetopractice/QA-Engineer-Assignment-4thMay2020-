package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.JsonParseException;

public class Utilities {

	public static Properties readPropertiesFile(String fileName) {
		FileInputStream fis = null;
		Properties prop = null;
		Path filePath = Paths.get(System.getProperty("user.dir"), "src/main/resources", fileName + ".properties");
		try {
			fis = new FileInputStream(filePath.toString());
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return prop;
	}

	public static Object jsonToObject(String response, Class cls) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Object obj = null;
		try {
			obj = objectMapper.readValue(response, cls);
		} catch (JsonMappingException e) {
			System.out.println("JsonMappingException");
			e.printStackTrace();
		} catch (JsonParseException e) {
			System.out.println("JsonParseException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
		return obj;
	}
	
	public static void writingIntoExcel (ArrayList<String> data) throws IOException {

        // Blank workbook 
        XSSFWorkbook workbook = new XSSFWorkbook(); 
  
        // Create a blank sheet 
        XSSFSheet sheet = workbook.createSheet("movies name"); 
  
        // Iterate over data and write to sheet 
        int dataSize = data.size();
        int rownum = 0; 
        for(int i = 0; i < data.size(); i++) {
        	Row row = sheet.createRow(rownum++); 
        	String objArr = data.get(i); 
        	Cell cell = row.createCell(0); 
        	cell.setCellValue(objArr);
        }	
        		
        Date dateToday = new Date();
        try {  
            FileOutputStream out = new FileOutputStream(new File("output/movie_name_"+dateToday+".xlsx")); 
            workbook.write(out); 
            out.close(); 
            System.out.println("movie_name.xlsx written successfully on disk."); 
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }

}
