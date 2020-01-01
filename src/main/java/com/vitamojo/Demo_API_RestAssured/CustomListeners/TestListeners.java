package com.vitamojo.Demo_API_RestAssured.CustomListeners;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.vitamojo.Demo_API_RestAssured.TestSetup.TestSetup;

public class TestListeners extends TestSetup implements ITestListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult arg0) {
		String exceptionMessage= Arrays.toString(arg0.getThrowable().getStackTrace());
		testLevelLog.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>"
				+ " \n");
		String failureLogg = "This Test case got Failed";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testLevelLog.get().log(Status.FAIL, m);
	}

	public void onTestSkipped(ITestResult arg0) {
		testLevelLog.get().skip("This test Case got Skipped");

	}

	public void onTestStart(ITestResult arg0) {
		System.out.println("******* On Test Start Execution Started********");

		System.out.println("Class Level Extent Object--->" + classLevelLog.get());
		ExtentTest test = classLevelLog.get().createNode(arg0.getName());
		testLevelLog.set(test);
		testLevelLog.get().log(Status.INFO,
				"<b>" + " Execution of Test Case:- " + arg0.getName() + " started" + "</b>");

	}

	public void onTestSuccess(ITestResult arg0) {
		String successMessage= "<b>"+"This Test Case is Passed"+"</b>";
		Markup m=MarkupHelper.createLabel(successMessage, ExtentColor.GREEN);
		testLevelLog.get().pass(m);

	}

}
