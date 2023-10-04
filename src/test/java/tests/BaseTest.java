package tests;


import extentreports.ExtentManager;
import logs.Log;
import org.example.TestBtnPages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
    
    protected TestBtnPages testBtn;

    // Get Os Name
    protected RemoteWebDriver driver;
    String detectOs = System.getProperty("os.name").toLowerCase();

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public void scroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1200)");
    }

    public void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void setUp() throws IOException {
        String rootProjectDirectoryPath = System.getProperty("user.dir");
        String resourcesDataWinPath = rootProjectDirectoryPath + "\\src\\test\\resources\\Download\\";
        String resourcesDataUnixPath = rootProjectDirectoryPath + "/src/test/resources/Download/";

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        if (detectOs.contains("win")) {
            chromePrefs.put("download.default_directory", resourcesDataWinPath);
        } else {
            chromePrefs.put("download.default_directory", resourcesDataUnixPath);
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

        driver.get("https://www.btn.co.id/");

        driver.manage().window().maximize();

        testBtn = new TestBtnPages(driver,wait);
    }

    @AfterClass
    public void getResult() {
         driver.quit();
    }

    @AfterSuite
    public void tearDown() {
        ExtentManager.extentReports.flush();
    }
}
