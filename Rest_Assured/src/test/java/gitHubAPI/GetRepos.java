package gitHubAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.matcher.RestAssuredMatchers.*;

public class GetRepos {
	
//	String authTokan="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";
	
	@Test
	public void getRepos() {
		
	  given()
		 .header("Authorization", "Bearer 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")
		 
	  .when()
		.get("https://api.github.com/users/RahulMahadik96/repos")
		
	  .then()
	    .statusCode(200)
	    .body("full_name",hasItem("RahulMahadik96/GitHub_API_Practical_Demo"))
	    .log().all();
		
	}
	
	

}
