package httpRequests;

import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.Utilities;


public class HTTPBase {
	
	private static String fileName = "testConfig";
	private static Properties prop =Utilities.readPropertiesFile(fileName);
	public static Response response;
	
    public static String setBaseUri(){
    	return RestAssured.baseURI = prop.getProperty("baseUrl");	
    }
    
}
