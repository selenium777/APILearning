package com.vitamojo.Demo_API_RestAssured.API;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Set;

import com.vitamojo.Demo_API_RestAssured.TestSetup.TestSetup;
import com.vitamojo.Demo_API_RestAssured.TestUtils.TestUtil;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateCustomerAPI extends TestSetup 
{
	public static Response sendRequestToUpdateCustomerWithValidAuthKey(String customerId,HashMap mapOfFields)
	{
		RequestSpecification requestSpecification=given().auth().basic(configProperty.getValidAuthKey(),"");
		if(mapOfFields.size()>0)
		{
			Set<String> keySet=mapOfFields.keySet();
			for(String key:keySet)
			{
				String value=mapOfFields.get(key).toString();
				requestSpecification=requestSpecification.param(key, value);
			}
		}
		Response response = requestSpecification.post(configProperty.getUpdateCustomerAPIEndPoint()+"/"+customerId);
		TestUtil.logResponseInReport(response);
		return response;
	}
}
