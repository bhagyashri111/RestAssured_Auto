package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.rest.objects.CustomerResponseFailed;
import com.qa.rest.objects.CustomerResponseSuccess;
import com.qa.rest.objects.CustomerResponseSuccess;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostCallWithJavaObject {
	
	@Test
		public void CreateCustomerTest() {
		
		// define URL

				RestAssured.baseURI = "http://restapi.demoqa.com/customer/register";

				// define the request

				RequestSpecification httpRequest = RestAssured.given();

				// 3.create json object

				org.json.simple.JSONObject requestJson = new org.json.simple.JSONObject();

				requestJson.put("FirstName", "ARydsfdfan1");
				requestJson.put("LastName", "AJudfhm1");
				requestJson.put("UserName", "ARyanfffgdfg111");
				requestJson.put("Password", "AdRyan11");
				requestJson.put("Email", "1Ryadgan@gmail.com");

				// 4.Add the header
				httpRequest.header("Content-Type", "application/json");

				// 5. add the json payload to body
				httpRequest.body(requestJson.toJSONString());

				// 6.Post the request and get the response
				Response response = httpRequest.post("/register");
			

				// 4. Get the response Body
				if(response.statusCode()==201) {
				String responseBody = response.getBody().asString();
				System.out.println("The Response Body is :" + responseBody);
		
				CustomerResponseSuccess customerresponse =	response.as(CustomerResponseSuccess.class);
				
				System.out.println(customerresponse.SuccessCode);
				System.out.println(customerresponse.Message);
		
				Assert.assertEquals("OPERATION_SUCCESS", customerresponse.SuccessCode);
				Assert.assertEquals("Operation completed successfully", customerresponse.Message);
				
				}else if(response.statusCode()==200) {
 
					
					String responseBody = response.getBody().asString();
					System.out.println("The Response Body is :" + responseBody);
			
					CustomerResponseFailed customerresponsefailed =	response.as(CustomerResponseFailed.class);
					
					System.out.println(customerresponsefailed.FailtId);
					System.out.println(customerresponsefailed.fault);
			
					Assert.assertEquals("User already exists", customerresponsefailed.FailtId);
					Assert.assertEquals("FAULT_USER_ALREADY_EXISTS", customerresponsefailed.fault);
					
					
				}
					
					

				
	}

}
