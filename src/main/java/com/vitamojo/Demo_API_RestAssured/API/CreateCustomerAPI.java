package com.vitamojo.Demo_API_RestAssured.API;

import java.util.Hashtable;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import com.vitamojo.Demo_API_RestAssured.TestSetup.TestSetup;
import com.vitamojo.Demo_API_RestAssured.TestUtils.TestUtil;

import io.restassured.response.Response;

public class CreateCustomerAPI extends TestSetup {

	public static Response sendPostRequestToCreateCustomerAPIWithValidAuthKey(Hashtable<String, String> data) {
		Response response = given().auth().basic(configProperty.getValidAuthKey(), "").param("email", data.get("email"))
				.param("description", data.get("description")).param("name", data.get("name"))
				.post(configProperty.getCreateCustomerAPIEndPoint());
		TestUtil.logResponseInReport(response);
		return response;
	}

	public static Response sendPostRequestToCreateCustomerAPIWithInValidAuthKey(Hashtable<String, String> data) {
		Response response = given().auth().basic(configProperty.getInvalidAuthKey(), "")
				.param("email", data.get("email")).param("description", data.get("description"))
				.param("name", data.get("name")).post(configProperty.getCreateCustomerAPIEndPoint());
		TestUtil.logResponseInReport(response);
		return response;
	}
}
