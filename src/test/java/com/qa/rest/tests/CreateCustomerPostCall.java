package com.qa.rest.tests;

import org.apache.http.HttpHost;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateCustomerPostCall {

	@Test

	public void CustomerPostcall() {

		// define URL

		RestAssured.baseURI = "http://restapi.demoqa.com/customer/register";

		// define the request

		RequestSpecification httpRequest = RestAssured.given();

		// 3.create json object

		org.json.simple.JSONObject requestJson = new org.json.simple.JSONObject();

		requestJson.put("FirstName", "Ryan");
		requestJson.put("LastName", "Juhm");
		requestJson.put("UserName", "Ryan11");
		requestJson.put("Password", "Ryan");
		requestJson.put("Email", "Ryan@gmail.com");

		// 4.Add the header
		httpRequest.header("Content-Type", "application/json");

		// 5. add the json payload to body

		httpRequest.body(requestJson.toJSONString());

		// 6.Post the request and get the response

		Response response = httpRequest.post("/register");

		// 4. Get the response Body

		String ResBody= response.getBody().asString();
		System.out.println("ResponseBody is : " + ResBody);

	

	}
}
