package com.vitamojo.Demo_API_RestAssured.TestSetup;

import java.io.IOException;
import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.vitamojo.Demo_API_RestAssured.TestUtils.ConfigProperties;
import com.vitamojo.Demo_API_RestAssured.TestUtils.ExcelReader;
import com.vitamojo.Demo_API_RestAssured.TestUtils.Extentmanager;

import io.restassured.RestAssured;

public class TestSetup {

	public static ConfigProperties configProperty; 
	public static ExtentReports extentReport;
	public static ThreadLocal<ExtentTest> classLevelLog=new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelLog=new ThreadLocal<ExtentTest>();
	public static ExcelReader excel = new ExcelReader("D:\\workspace\\Demo_API_RestAssured\\src\\test\\resources\\testData\\TestDataSheet.xlsx");
	
	@BeforeSuite
	public void setUpFramework() throws IOException
	{
		configProperty=ConfigFactory.create(ConfigProperties.class);
		// add lines for the archive reeport
		RestAssured.baseURI=configProperty.getBaseURI();
		RestAssured.basePath=configProperty.getBasePath();
		extentReport=Extentmanager.getExtent("D:\\workspace\\Demo_API_RestAssured\\src\\main\\resources\\TestReport\\TestReport.html");
	}
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("Before Test executed...");
	}
	
	@BeforeClass
	public synchronized void beforeClass()
	{
		System.out.println("Before class executed...");
		ExtentTest test = extentReport.createTest(getClass().getSimpleName());
		classLevelLog.set(test);
	}
	@BeforeMethod
	public void beforeMethod(Method method)
	{
		System.out.println("Execution of Test Case:- "+method.getName() +" Started");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult testCaseResult)
	{
		System.out.println("Execution of Test Case:- "+testCaseResult.getName()+" finished");
	}
	
	@AfterClass
	public void afterClass()
	{
		System.out.println("after class execution...");
	}
	
	@AfterTest
	public void afterTest()
	{
		System.out.println("after test eexecution...");
	}
	
	@AfterSuite
	public void tearDownFramework() throws Exception
	{
		System.out.println("after suite execution...");
		extentReport.flush();
	}
}
