package com.vitamojo.Demo_API_RestAssured.TestUtils;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.vitamojo.Demo_API_RestAssured.TestSetup.TestSetup;

public class Extentmanager extends TestSetup {
	
	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;

	public static ExtentReports getExtent(String filePath)
	{
		if(extent !=null)
		{
			return extent;
		}else{
			extent= new ExtentReports();
			extent.attachReporter(Extentmanager.getHtmlReporter(filePath));
			extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
			return extent;
		}
	}
	public static ExtentHtmlReporter getHtmlReporter(String filePath)
	{
		htmlReporter=new ExtentHtmlReporter(filePath);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/src/test/resources/extentConfingXML/ReportsConfig.xml");
		return htmlReporter;
	}

}
