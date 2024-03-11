package tests;


import extentreports.ExtentManager;
import logs.Log;

import org.btn.Pages.CalculatePrice.Property_price;
import org.btn.Pages.Dashboard.Dashboard_pages;
import org.btn.Utility.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    // protected static WebDriver driver;
    protected WebDriverWait wait;
    protected Data data;

    protected Dashboard_pages dashboard_pages;
    protected Property_price property_price;
    protected String baseUrl = "https://www.btnproperti.co.id";

    // Get Os Name
    protected RemoteWebDriver driver;
    String detectOs = System.getProperty("os.name").toLowerCase();

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public static void scrollDown(WebDriver driver, int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }

    public static void slowScrollToEnd(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long scrollHeight = (long) js.executeScript("return document.body.scrollHeight");

        // Scroll perlahan hingga akhir halaman
        for (long scroll = 0; scroll < scrollHeight; scroll += 10) {
            js.executeScript("window.scrollTo(0, " + scroll + ")");
            try {
                Thread.sleep(50); // Sesuaikan dengan kecepatan scroll yang diinginkan (ms)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int generateRandomPrice() {
        Random rand = new Random();
        int min = 1000000; 
        int max = 10; 
        int randomNumber = rand.nextInt(max) + 1; 
        int randomPrice = randomNumber * 10000000; 
        
        return randomPrice;
    }

    public static int change_years_to_month(int tahun){
        int bulan = tahun * 12;
        return bulan;
    }

    public static double calculate_property_prices(double penghasilan, double pengeluaran, int jumlahBulan) {
        double pendapatanBersih = (penghasilan - pengeluaran) * jumlahBulan / 3.0;
        return pendapatanBersih;
    }

    @BeforeTest
    public void setUp() throws IOException {
        String rootProjectDirectoryPath = System.getProperty("user.dir");
        String resourcesDataWinPath = rootProjectDirectoryPath + "\\src\\test\\resources\\driver\\";
        String resourcesDataUnixPath = rootProjectDirectoryPath + "/src/test/resources/driver/";

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        if (detectOs.contains("win")) {
            chromePrefs.put("driver.default_directory", resourcesDataWinPath);
        } else {
            chromePrefs.put("driver.default_directory", resourcesDataUnixPath);
        }
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-notifications", "--start-maximized", "window-size=1920,1080", "--disable-infobars", "--remote-allow-origins=*");
        options.setHeadless(Boolean.parseBoolean(System.getProperty("headless", "false")));
        options.setExperimentalOption("prefs", chromePrefs);

        // Chrome Driver Automatically initializes
        if(System.getProperty("driver", "local").equals("server")) {
            driver = new RemoteWebDriver(new URL(""), options);
        } else {
            driver = new ChromeDriver(options);
        }
        Log.info("Setup the browser");
        // Start in Chrome
        options.setAcceptInsecureCerts(true);
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Log.info("Browser is opening");

        driver.manage().window().maximize();

        data = new Data();

        dashboard_pages = new Dashboard_pages(driver, wait);
        property_price = new Property_price(driver, wait);
    }

    @AfterClass
    public void getResult() {
        //  driver.quit();
    }

    @AfterSuite
    public void tearDown() {
        ExtentManager.extentReports.flush();
    }
}
