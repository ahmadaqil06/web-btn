package listeners;

import com.aventstack.extentreports.Status;


import static extentreports.ExtentTestManager.getTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import tests.BaseTest;

public class Retry implements IRetryAnalyzer {

    private int count = 0;
    
    @Override
    public boolean retry(ITestResult iTestResult) {
        
        if (!iTestResult.isSuccess()) {
            int maxTry = 1;
            if (count < maxTry) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);
                extendReportsFailOperations(iTestResult);
                return true;
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
            extendReportsPassOperations(iTestResult);
        }
        return false;
    }

    public void extendReportsPassOperations(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.PASS, "Test Pass",
            getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    public void extendReportsFailOperations(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Test Failed",
            getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }
}
