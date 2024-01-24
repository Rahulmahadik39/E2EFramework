package demopakage;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class TestRest_Assured_FirstAPITest {
	
	@Test
	void getUser() {
			         
		Response response=get("https://reqres.in/api/users?page=2");
		
		System.out.println(response.asString());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		System.out.println(response.getHeader("content-type"));
		System.out.println(response.getTime());
		
		
		 /*
	    when()
		    .get("https://reqres.in/api/users/2")
		.then()
		     .statusCode(200)		    
		     .body("data.id", equalTo(2))
		     .body("data.email",equalTo("janet.weaver@reqres.in"));
	         
		    // .log().all();
	     
		 */ 
	}

}
