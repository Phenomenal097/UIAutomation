package com.qa.opencart.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("OpenCart Test Report");
        spark.config().setReportName("Playwright UI Tests");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Browser", "Chromium");
        extent.setSystemInfo("Environment", "QA");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test was skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}