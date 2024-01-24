package bookingAPITest;

import static io.restassured.RestAssured.given;

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

import io.restassured.response.Response;
import pojoForRestAssure.BookingDates;
import pojoForRestAssure.BookingDetails;
import pojoForRestAssure.UserCredentials;

public class BookingAPIwithExcelTestDataTest {
	
	@Test(dataProvider = "ExcelTestData")
	public void apiTestwithExcelData(Map<String, String> testData) throws JsonProcessingException {
		
		    int price=Integer.parseInt(testData.get("TotalPrice"));
		    String contentType=testData.get("ContentType");
		    String tokenReqURI=testData.get("TPostURI");
		    String bookingURI= testData.get("BPostURI");
		    String getBookingURI=testData.get("GetURI");
		
		    BookingDates bdt1=new BookingDates(testData.get("CheckinDate"), testData.get("CheckoutDate"));
		
		    BookingDetails bdl=new BookingDetails(testData.get("FirstName"), testData.get("LastName"), price, true, bdt1, testData.get("additionalneeds"));
            
		    UserCredentials uc=new UserCredentials(testData.get("username"),testData.get("password"));
		    
		    ObjectMapper objectM=new ObjectMapper();
		    
		  
		   String tokenReqBody=objectM.writerWithDefaultPrettyPrinter().writeValueAsString(uc);
		   String bookingreqBody= objectM.writerWithDefaultPrettyPrinter().writeValueAsString(bdl);
		    
		    
		    Response tokenResponse= given()
		    		                       .header("Content-Type",contentType)
		    		                       .baseUri(tokenReqURI)
		    		                       .body(tokenReqBody)
		    		                       
		    		                   .when()
		    		                       .post()
		    		                   .then()
		    		                       .extract()
		    		                       .response();
		   
		    int tokenStatusCode=tokenResponse.getStatusCode();
		    
		    Assert.assertEquals(tokenStatusCode, 200,"Failed: unexpected status code");
		    System.out.println("StapPass: token is properly genreted");
		    
		    String token= tokenResponse.jsonPath().getString("token");
		    
		    Response bookingResponse= given()
		                                   .header("Authorization","Bearer "+token)
		                                   .header("Content-Type",contentType)
		                                   .baseUri(bookingURI)
		                                   .body(bookingreqBody)
		                             .when()
		                                   .post()
		                             .then()
		                                   .extract()
		                                   .response();
		    
		    String bookingid= bookingResponse.jsonPath().getString("bookingid");
		    
		    
		    Assert.assertNotNull(bookingid,"StepFail: booking not properly done");  
		    System.out.println("StepPass: Booking Done");
		    
		    Response getBookingResponse = given()
		                                 .header("Authorization","Bearer "+token)
		                                 .header("Content-Type",contentType)
		                                 .baseUri(getBookingURI+""+bookingid)
		                             .when()
		                                 .get()
		                             .then()
		                                 .extract()
		                                 .response();
		    
		    int getSCode=getBookingResponse.getStatusCode();
		    Assert.assertEquals(getSCode, 200,"StepFail: Booking Detail not fatching");
		    System.out.println("StepPass: Booking details successfully fatched");
		    
		    System.out.println();
		    System.out.println("Booking id:"+bookingid);	
		    System.out.println("Booking Details:");
		    System.out.println(getBookingResponse.getBody().asPrettyString());
		                                 
		   
	
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
