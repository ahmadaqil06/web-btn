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
        property_price.total_income("10000000");
        getTest().log(Status.INFO, "Scroll to end page");
        property_price.expenditure("2000000");

        property_price.select_time(1);
        scrollDown(driver, 600);
        delay();
        property_price.calculate();
        property_price.get_calculation_results();
        System.out.println( property_price.get_calculation_results());
        getTest().log(Status.INFO, "Click hitung harga di tools");
    }

   

}
