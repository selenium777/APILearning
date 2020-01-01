package com.vitamojo.Demo_API_RestAssured.TestUtils;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import com.vitamojo.Demo_API_RestAssured.TestSetup.TestSetup;


public class Data extends TestSetup {
	@DataProvider(name="data")
	public static Object[][] getData(Method m)
	{
		String sheetName=configProperty.getTestDataSheetName();
		
		int rowCount = excel.getRowCount(sheetName);
		String testCaseName=m.getName();
		int testCaseRowNum=1;
		for(testCaseRowNum=1;testCaseRowNum<=rowCount;testCaseRowNum++)
		{
			String testCaseNameInExcelFile=excel.getCellData(sheetName, 0, testCaseRowNum);
			
			if(testCaseNameInExcelFile.equalsIgnoreCase(testCaseName))
			{
				break;
			}
		}
		
	//Checking total rows in the test case
		Reporter.log("Test case Starts from :- "+testCaseRowNum, true);
		int dataStartRowNum=testCaseRowNum+2;
		int testRows =0;
		while(!excel.getCellData(sheetName, 0,dataStartRowNum+testRows).equalsIgnoreCase("endOfTestData"))
		{
			testRows++;
		}
		//Checking total columns in test case
		int colStartColNum=testCaseRowNum+1;
		int testCols=0;
		while(!excel.getCellData(sheetName, testCols, colStartColNum).equals(""))
		{
			testCols++;
		}
		Object[][] data = new Object[testRows][1];
		int i=0;
		for(int rNum=dataStartRowNum;rNum<(dataStartRowNum+testRows);rNum++)
		{
			Hashtable<String,String> table = new Hashtable<String,String>();
			for(int cNum=0;cNum<testCols;cNum++)
			{
				String colName=excel.getCellData(sheetName, cNum, colStartColNum);
				String testData=excel.getCellData(sheetName, cNum, rNum);
				table.put(colName, testData);
			}
			data[i][0]=table;
			i++;
		}
		return data;
	}

}
