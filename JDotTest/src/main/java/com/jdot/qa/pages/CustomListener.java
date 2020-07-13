package com.jdot.qa.pages;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.jdot.qa.base.TestBase;

public class CustomListener extends TestBase implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailure(ITestResult result) {
		// -- Checking Failures in tests
		System.out.println("FAILED TEST");
		failed(result.getMethod().getMethodName()); //Calling failed method from Base Class
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
	
	
	

}
