package com.vitamojo.Demo_API_RestAssured.TestCases;

import java.util.HashMap;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vitamojo.Demo_API_RestAssured.API.UpdateCustomerAPI;
import com.vitamojo.Demo_API_RestAssured.TestSetup.TestSetup;
import com.vitamojo.Demo_API_RestAssured.TestUtils.Data;
import com.vitamojo.Demo_API_RestAssured.TestUtils.TestUtil;

import io.restassured.response.Response;

public class ValidateUpdateCustomerAPI extends TestSetup{

	@Test(dataProviderClass=Data.class,dataProvider="data")
	public void validateUpdateCustomerAPIWithValidAuthKey(Hashtable<String,String> data)
	{
		testLevelLog.get().assignAuthor("Ankit Singh");
		HashMap mapOfField=new HashMap();
		mapOfField.put("name",data.get("name"));
		mapOfField.put("balance",Integer.parseInt(data.get("balance")));
		Response response = UpdateCustomerAPI.sendRequestToUpdateCustomerWithValidAuthKey("cus_GRDnYilwrt5hsI", mapOfField);
		TestUtil.logResponseInReport(response);
		Assert.assertEquals(response.getStatusCode(),Integer.parseInt(data.get("expectedStatusCode")));
	}
}
