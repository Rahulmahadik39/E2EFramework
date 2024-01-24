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
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoForRestAssure.BookingDates;
import pojoForRestAssure.BookingDetails;
import pojoForRestAssure.UserCredentials;

public class UpdateBookingAPITest {
	
	@Test(dataProvider = "ExcleData")
	public void updateBooking(Map<String,String> testdata) throws JsonProcessingException {
		
		
		String tPostURI=testdata.get("TPostURI");
		String bookURI=testdata.get("BPostURI");
		String getBookURI=testdata.get("GetURI");
		String updateBookURI=testdata.get("PutURI");
		String contentType=testdata.get("ContentType");
		
		int totalPrice=Integer.parseInt(testdata.get("TotalPrice"));
		boolean depositPay=Boolean.parseBoolean(testdata.get("depositpaid"));
		
		BookingDates bDate=new BookingDates(testdata.get("CheckinDate"),testdata.get("CheckoutDate"));
	
		BookingDetails bDetails=new BookingDetails(testdata.get("FirstName"),testdata.get("LastName"),totalPrice,depositPay,bDate,testdata.get("additionalneeds"));
		
		UserCredentials uc=new UserCredentials(testdata.get("username"),testdata.get("password"));
		
		totalPrice=Integer.parseInt(testdata.get("UpdatedTPrice"));
		
		BookingDetails updatedDetails=new BookingDetails(testdata.get("FirstName"),testdata.get("UpdatedLastName"),totalPrice,depositPay,bDate,testdata.get("UpdatedAddNeed"));
		
		ObjectMapper objMapper=new ObjectMapper();
		
		String bookingBody=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bDetails);
		String tokenBody=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(uc);
		String UpdateBody=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(updatedDetails);
	
		System.out.println(UpdateBody);
		
		System.out.println();
		RequestSpecBuilder reqBuilder=new RequestSpecBuilder();
		reqBuilder.setContentType(contentType);
		
		RequestSpecification reqSpec=reqBuilder.build();
		
		Response tokenResponse= RestAssured.given()
				                                 .spec(reqSpec)
				                                 .baseUri(tPostURI)
				                                 .body(tokenBody)
				                           .when()
				                                 .post()
				                           .then()
				                                 .extract()
				                                 .response();
	   
		int tokenStatusCode=tokenResponse.getStatusCode();
		Assert.assertEquals(tokenStatusCode, 200,"StepFail: Token not created");
		System.out.println("StepPass: Token Successfuly created");
		
		String token=tokenResponse.jsonPath().getString("token");
		Assert.assertNotNull(token,"StapFail: Not able to access token");
		
		reqBuilder.addHeader("Authorization", "Bearer "+token);
		reqSpec=reqBuilder.build();
		
		Response bookingResponse= RestAssured.given()
                                                .spec(reqSpec)
                                                .baseUri(bookURI)
                                                .body(bookingBody)
                                             .when()
                                                .post()
                                             .then()
                                                .extract()
                                                .response();
		
		int bookingStatusCode=bookingResponse.getStatusCode();
		Assert.assertEquals(bookingStatusCode, 200,"StepFail: Booking not Complited");
		System.out.println("StepPass: Booking Successfuly Complited");
		
		int bookingID=bookingResponse.jsonPath().getInt("bookingid");
		Assert.assertNotNull(bookingID,"StepFail: BookingId not Generating");
		System.out.println("StepPass:Got the BookingID");
		
		System.out.println();
		Response getBookDetailsResponse= RestAssured.given()
                                                          .spec(reqSpec)
                                                          .baseUri(getBookURI+bookingID)
                                                     .when()
                                                          .get()
                                                     .then()
                                                          .log().all()
                                                         .extract()
                                                          .response();
		int getbookDetailsStatusCode=getBookDetailsResponse.getStatusCode();
		Assert.assertEquals(getbookDetailsStatusCode, 200,"StepFail:Unable to access the booking Details");
	
		/*
		
		Response updatedBookingResponse= RestAssured.given()
                                                        .spec(reqSpec)
                                                        .baseUri(updateBookURI+bookingID)
                                                        .body(UpdateBody)
                                                    .when()
                                                        .post()
                                                    .then()
                                                         .log().all()
                                                        .extract()
                                                         .response();
		
		int updateStatusCode=updatedBookingResponse.getStatusCode();
		Assert.assertEquals(updateStatusCode, 200,"StepFail: Update Booking Unsuccessful");
		System.out.println("StepPass: Booking Updated successful");
        	*/
        System.out.println();
	  
		
	}
	
	@DataProvider(name="ExcleData")
	public Object[][] dataProvider() throws FilloException {
		

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
