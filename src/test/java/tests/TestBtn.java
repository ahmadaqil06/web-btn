package tests;

import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;
import static extentreports.ExtentTestManager.getTest;
import static extentreports.ExtentTestManager.startTest;

public class TestBtn extends BaseTest {
    
    @Test(priority = 0, description = "Dashboard BTN.co.id")
    public void testBtnConvertValas() throws InterruptedException{
        startTest("Go To Dashboard", "Go To Dashboard BTN.co.id");
        testBtn.drpDwnValas();
        getTest().log(Status.INFO, "Hover mouse into dropdown valas");
        delay();
        testBtn.clickConverterKurs();
        getTest().log(Status.INFO, "Click Button E-Convert");
    }

    @Test(priority = 1, description = "Move to Valas Information")
    public void moveValasConvert() throws InterruptedException{
        startTest("Move to converter valas Pages", "Move to converter valas Pages");
        testBtn.verifyValasInformation();
        getTest().log(Status.INFO, "Verify that you have moved pages and the text is there");
        scrollDown(driver, 1200);
        testBtn.inptNominal("2");
        getTest().log(Status.INFO, "Input nominal USD");
        testBtn.selectCurrentCode();
        getTest().log(Status.INFO, "Click Drop Down Current Code");
        testBtn.clickHitung();
        getTest().log(Status.INFO, "Click Calculation");
        testBtn.verifyHasil();
        getTest().log(Status.INFO, "Verify the results of converting USD to IDR");
    }

}
