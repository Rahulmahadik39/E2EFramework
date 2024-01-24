package gitHubAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import groovy.util.logging.Log;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.matcher.RestAssuredMatchers.*;
public class CreateRepo {
	
	@Test
	public void createGitHubRepo() {
		
		given()
		   .header("Authorization", "Bearer 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")
		   .body("{\"name\":\"RestAssure_Post_Test02\"}")
		
		.when()
		   .post("https://api.github.com/user/repos")
		
		.then()
		   .statusCode(201)
		   //.body("private", hasItem("false") )
		   
		   .log().all();
		   
		   
	}
	

}
