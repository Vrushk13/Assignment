package com_VE3_Test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportListener implements ITestListener {
	private ExtentReports extent;
	protected ExtentTest test;

	public void onStart(ITestContext context) {
		String reportName = "ExtentReport.html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
