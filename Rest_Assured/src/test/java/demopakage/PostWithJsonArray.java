package demopakage;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.json.JSONArray;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostWithJsonArray {
	
	@Test
	public void createAndValidate() {
		
	Response response= RestAssured.given()
				                       .baseUri("https://reqres.in/api/users?page=2")
				                    .when()
				                        .get()
				                    .then()
				                        .statusCode(200)
				                        .log().all()
				                        .extract()
				                        .response();
	
	             
	                    
	                  
	
	       String usernames = response.jsonPath().getString("data.first_name[0]");
	      System.out.println(usernames);
	       
	           List<String> bodyList   =  response.jsonPath().getList("data.id");
	           System.out.println(bodyList);
	           
	           System.out.println(response.jsonPath().getString(usernames));
				                       
	}
	
	

}
