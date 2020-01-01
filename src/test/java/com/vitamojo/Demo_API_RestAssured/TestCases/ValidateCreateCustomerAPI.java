package com.vitamojo.Demo_API_RestAssured.TestCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vitamojo.Demo_API_RestAssured.API.CreateCustomerAPI;
import com.vitamojo.Demo_API_RestAssured.TestSetup.TestSetup;
import com.vitamojo.Demo_API_RestAssured.TestUtils.Data;
import com.vitamojo.Demo_API_RestAssured.TestUtils.TestUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ValidateCreateCustomerAPI extends TestSetup {

	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateCreateCustomerAPIWithValidAuthKey(Hashtable<String, String> data) 
	{
		testLevelLog.get().assignAuthor("Ankit Singh");
		
		Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithValidAuthKey(data);
		JsonPath responseJson = response.jsonPath();
		
		response.prettyPrint();
		
		//logging response in the report
		TestUtil.logResponseInReport(response);
		
		//Validating the response Status Code
		Assert.assertEquals(response.getStatusCode(),Integer.parseInt(data.get("expectedStatusCode")));
		testLevelLog.get().info("response status code is as expected...");
		
		//validating that the email in the response is same as of the email passed
		Assert.assertEquals(responseJson.get("email"),data.get("email"));
		testLevelLog.get().info("the email in the response is as per expected value...");
		
		Assert.assertEquals(responseJson.get("description"),data.get("description"));
		
		Assert.assertEquals(responseJson.get("name"),data.get("name"));
		
		if(TestUtil.checkJsonHasKey("id", response))
		{
			Assert.assertNotNull(responseJson.get("id"));
		}
		else
		{
			Assert.fail("id field is not available in the response");
		}
	}
	
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateCreateCustomerAPIWithInvalidAuthKey(Hashtable<String, String> data) 
	{
		testLevelLog.get().assignAuthor("Ankit Singh");
		
		Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInValidAuthKey(data);
		JsonPath responseJson = response.jsonPath();
		
		response.prettyPrint();
		
		//logging response in the report
		TestUtil.logResponseInReport(response);
		
		//Validating the response Status Code
		Assert.assertEquals(response.getStatusCode(),Integer.parseInt(data.get("expectedStatusCode")));
		testLevelLog.get().info("response status code is as expected...");
		
		//validating the response does not have id field
		Assert.assertFalse(TestUtil.checkJsonHasKey("id", response));
		
		
	}
}
