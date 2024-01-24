package demopakage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import excelLabrary.ExlAPIData;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;



public class Get_Request_Test {
   
	
	
	@Test
	public void get_Users() {
 
		when()
		     .get("https://reqres.in/api/users?page=2")

		.then()
		     .statusCode(200)
		     .body( "page", equalTo(2))
		     .body("data.id[0]", equalTo(7)) // single data validating under the body of Response with Multiple block details.
		     .body("data.first_name", hasItems("Michael","Lindsay","Tobias")) // Multiple data validating under the body of Response with Multi block details.
	         .header("Content-Type", equalTo("application/json; charset=utf-8"))
		     .log().all();
		    
	}

}
