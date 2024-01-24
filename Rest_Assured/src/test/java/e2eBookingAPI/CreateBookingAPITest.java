package e2eBookingAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojoForRestAssure.BookingDates;
import pojoForRestAssure.BookingDetails;
import pojoForRestAssure.UserCredentials;

public class CreateBookingAPITest {
	
	@Test(dataProvider = "ExcelTestData")
	public void createBookingAPItest(Map<String, String> testData) throws JsonProcessingException {
		
		String tokenCreateURI=testData.get("TPostURI");
		String bookingCreateURI=testData.get("BPostURI");
		String getBookingURI=testData.get("GetURI");
		int totalPrice=Integer.parseInt(testData.get("TotalPrice"));
		boolean depositpaid=Boolean.parseBoolean(testData.get("depositpaid"));
		String contentType=testData.get("ContentType");
		
		
		BookingDates date=new BookingDates(testData.get("CheckinDate"),testData.get("CheckoutDate"));
		
		BookingDetails details=new BookingDetails(testData.get("FirstName"),testData.get("LastName"),totalPrice,depositpaid,date, testData.get("additionalneeds"));
		
		UserCredentials uc=new UserCredentials(testData.get("username"),testData.get("password"));
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		String tokenBody=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(uc);
		String bookingBody=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(details);
		
		System.out.println("");
		
		Response  tokenresponse= RestAssured.given()
				                           .header("Content-Type",contentType)
				                           .baseUri(tokenCreateURI)
				                           .body(tokenBody)
				                       .when()
				                           .post()
				                       .then()
				                           .extract()
				                           .response();
		
		int tokenStatus=tokenresponse.getStatusCode();
		Assert.assertEquals(tokenStatus, 200,"StepFail: Token is not created");
		System.out.println("StepPass: Token Created");
		
		String token=tokenresponse.jsonPath().getString("token");
		
		Response bookingResponse= RestAssured.given()
				                                   .header("Authorization","Bearer"+token)
				                                   .header("Content-Type",contentType)
				                                   .baseUri(bookingCreateURI)
				                                   .body(bookingBody)
				                             .when()
				                                   .post()
				                             .then()
				                                   .extract()
				                                   .response();
		
	    int bookingStatus=bookingResponse.getStatusCode();
	    Assert.assertEquals(bookingStatus, 200,"StepFail: booking unsuccessful");
	    System.out.println("StepPass: Booking Successful");
	    
	    int bookingID=bookingResponse.jsonPath().getInt("bookingid");
	    Assert.assertNotNull(bookingID,"StepFail: Unable to get the BookingID");
	    System.out.println("StepPass: Got the BookingID ");
	    
	    System.out.println("");
	    System.out.println("BookingID: "+bookingID);
		
		Response getResponse=RestAssured.given()
				                             .header("Authorization","Bearer"+token)
			                                 .header("Content-Type",contentType)
			                                 .baseUri(getBookingURI+bookingID)
			                            .when()
			                                 .get()
			                            .then()
			                                 .extract()
			                                 .response();
		
		String getBookingDetails=getResponse.jsonPath().getString("");
		System.out.println("Booking Details:");
		System.out.println(getBookingDetails);
			                            
		System.out.println("");	                      
			                                   
		
	}
	
	@DataProvider(name="ExcelTestData")
	public Object[][] getTestData() throws FilloException {
		
		String query="select * from Sheet1 where Run='Yes'";
		
		Object[][] objArray=null;
		Map<String, String> testData=null;
		List<Map<String, String>> testDataList=null;
		
		Fillo fillo=new Fillo();
		Connection connection=null;
		Recordset recordset=null;
		
		
		connection= fillo.getConnection("C:\\Users\\Rahul\\NewDoc\\APITestdata.xlsx");
		recordset= connection.executeQuery(query);
		
		testDataList =new ArrayList<Map<String,String>>();
		
		while(recordset.next()){
			testData=new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
			
			for(String field:recordset.getFieldNames()) {
				testData.put(field, recordset.getField(field));
			}
			
			testDataList.add(testData);
		}
		
		objArray=new Object[testDataList.size()][1];
		
		for(int i=0;i<testDataList.size();i++) {
			objArray[i][0]=testDataList.get(i);
		}
		
		return objArray;
	}

}
