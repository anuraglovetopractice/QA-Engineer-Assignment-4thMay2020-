package httpRequests;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class GetRequest extends HTTPBase {
	
	public Response httpGetRequest(String endpoint) {
		return RestAssured.given().request(Method.GET, endpoint);
	}
}