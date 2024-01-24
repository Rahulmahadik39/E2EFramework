package demopakage;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostAPIRequestUsingTextFile {
	
	@Test
	public void postAPIRequest() throws IOException {
		
		String postAPIReqBody= FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQUEST_BODY),"UTF-8");
		 
		System.out.println(postAPIReqBody);
		
		Response response= RestAssured.given()
				                        .header("Content-Type","application/json")
				                        .header("Authorization","Bearer 22975fa23c65f72")
				                        .baseUri("https://restful-booker.herokuapp.com/booking")
				                        .body(postAPIReqBody)
				                     .when()
				                        .post()
				                     .then()
				                        .log().all()
				                        .body("bookingid", Matchers.notNullValue())
				                        .extract()
				                        .response();
		
	    System.out.println(response.jsonPath().getString("bookingid"));
				                     

		
		  
		
	}

}
