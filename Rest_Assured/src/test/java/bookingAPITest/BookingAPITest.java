package bookingAPITest;




import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojoForRestAssure.BookingDates;
import pojoForRestAssure.BookingDetails;

public class BookingAPITest {
	
	private static final Logger log= LogManager.getLogger(BookingAPITest.class);
	
	@Test
	public void bookingAPItest() throws JsonProcessingException {
		//System.setProperty("log4j.configurationFile","C:\\Users\\Rahul\\git\\FinalProject\\CucumberDemo1\\FinalProject\\FinalProject\\FinalProject\\CucumberDemo1\\src\\main\\java\\resources\\Log4j2.properties");
		
		
		log.info("Test started");
	
		//serialization 
	  BookingDates bdt1=new BookingDates("2023-10-10", "2023-10-11");
	
	  BookingDetails bdl=new BookingDetails("Rahul", "shaha", 1000, true, bdt1, "Breakfast");
	
	  ObjectMapper objMapper1=new ObjectMapper();
	
	  String requistbody=objMapper1.writerWithDefaultPrettyPrinter().writeValueAsString(bdl);
	
	  System.out.println(requistbody);
	  
	  
	    // De-serialization 
	  
	  
	// BookingDetails booking2= objMapper.readValue(requistbody, BookingDetails.class);
	
	// System.out.println(booking2.getFirstname());
	// System.out.println(booking2.getTotalprice());
	// System.out.println(booking2.getBookingdates().getCheckout());
	 
	 Response response= RestAssured.given()
			                         .contentType("application/json")
			                         .body(requistbody)
			                         .baseUri("https://restful-booker.herokuapp.com/booking")
			                       .when()
			                         .post()
			                       .then()
			                         .extract()
			                         .response();
	// .log().all()
	      
	             String responsBody=response.asString();
	             log.info(responsBody);
	 
	      System.out.println(response.statusCode());
	      int bookingID=response.jsonPath().getInt("bookingid");
	      
	      log.info("bookingID:"+bookingID);
	       
	        Response response2=given()
	        		                 .contentType("application/json")
	        		                 .baseUri("https://restful-booker.herokuapp.com/booking/"+bookingID)
	        		              .when()
	        		                 .get()
	        		              .then()
	        		                 .log().all()
	        		                 .extract()
	        		                 .response();
	                          
	        log.info(response2.statusCode());
	 
	}

 
}
