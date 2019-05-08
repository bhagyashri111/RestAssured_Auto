package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherGetTests {

	@Test(priority =1)

	public void getWeatherdetails() {

		// 1.define base URL
		// http://restapi.demoqa.com/utilities/weather/city

		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// 2. define http request

		RequestSpecification httpRest = RestAssured.given();

		// 3. make a request/execute the request

		// RestAssured.request(Method.GET,
		// "http://restapi.demoqa.com/utilities/weather/city", "/Latur");
		Response response = RestAssured.request(Method.GET, "/Latur");
		// System.out.println(response.getBody().asString());
		
		// 4. Get the response Body

		String ResponseBody = response.getBody().asString();
		System.out.println("ResponseBody is : " + ResponseBody);
		
		// validate the city name or key or value
		
		Assert.assertEquals(ResponseBody.contains("Latur"), true);

		// 5. Get the status code and validate it.

		int statusCode = response.getStatusCode();
		System.out.println("The response code is :" + statusCode);

		Assert.assertEquals(statusCode, 200);

		System.out.println("The status line is :" + response.getStatusLine());

		// 6. Get the headers

		Headers headers = response.getHeaders();

		System.out.println(headers);
		String ContentType = response.getHeader("Content-Type");
		System.out.println("The content type is :" + ContentType);
		
		String ContentLength = response.getHeader("Content-Length");
		System.out.println("The content Length is :" + ContentLength);
		
		//7.get the key value using json path
		
		JsonPath jsonPathValue  = response.jsonPath();
		
		String city = jsonPathValue.get("City");
		System.out.println("The value of city is:" + city);
		
		String temp = jsonPathValue.get("Temperature");
		System.out.println("The value of temperature is:" + temp);
		
		String humid = jsonPathValue.get("Humidity");
		System.out.println("The value of huidity is:" + humid);
		
		String wetDec = jsonPathValue.get("WeatherDescription");
		System.out.println("The value of weatherdescription is:" + wetDec);
		
		String wind = jsonPathValue.get("WindSpeed");
		System.out.println("The value of WindSpeed is:" + wind);
		
		String windDir = jsonPathValue.get("WindDirectionDegree");
		System.out.println("The value of winddirection is:" + windDir);
		

	}
	

}
