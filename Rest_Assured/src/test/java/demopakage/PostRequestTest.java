package demopakage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import excelLabrary.ExlAPIData;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import net.bytebuddy.agent.builder.AgentBuilder.Identified.Extendable;

public class PostRequestTest {
	
ExlAPIData exd;
	
	@BeforeClass
	public void getData() throws EncryptedDocumentException, IOException {
		exd=new ExlAPIData();
		exd.setData();
	}
	
	@Test
	public void postRTest() {
		
		System.out.println(exd.getUserList());
		Object body= "{ \"name\": \"morpheus\",\"job\": \"leader\" }";
		String reqURL="https://reqres.in/api/users";
		
		given()	
		 .body(body)		  
		.when()
		   .post(reqURL)
		   
		.then()
		    .statusCode(201)
		    .body("id", notNullValue())
		    .header("Content-Type", "application/json; charset=utf-8")
		   // .body("name", equalTo("morpheus"))
		  //  .body("createdAt", hasItem("2023-09-16T11:54:01.248Z"))
		    
		    .log().all();
		 
	}
	

}
