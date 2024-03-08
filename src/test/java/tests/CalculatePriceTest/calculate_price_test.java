package tests.CalculatePriceTest;

import com.aventstack.extentreports.Status;

import tests.BaseTest;

import org.testng.annotations.Test;
import static extentreports.ExtentTestManager.getTest;
import static extentreports.ExtentTestManager.startTest;

import java.io.IOException;

public class calculate_price_test extends BaseTest {

    @Test(priority = 0, description = "[Positive] Calculate Price")
    public void calculate_price() throws InterruptedException, IOException {
        driver.get(baseUrl + "/tools/hitung-harga-properti");
        startTest("[Positive] Calculate price", "calculate price kpr");
        if (!dashboard_pages.isDashboardTextPresent(driver)) {
            driver.navigate().refresh();
        }
        property_price.total_income("");
        getTest().log(Status.INFO, "Scroll to end page");
        
        getTest().log(Status.INFO, "Click hitung harga di tools");
    }

   

}
