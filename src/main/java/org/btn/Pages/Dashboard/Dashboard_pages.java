package org.btn.Pages.Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dashboard_pages {
     WebDriver driver;
    WebDriverWait wait;

    public Dashboard_pages(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 100);
    }

    public static boolean isDashboardTextPresent(WebDriver driver) {
        WebElement dashboardElement = driver.findElement(By.xpath("//img[@alt='BTN Properti Logo']"));
        return dashboardElement.isDisplayed();
    }

    public void tools_calculate_price(){
        By calculatePrice = By.xpath("//a[normalize-space()='Hitung Harga']");
        WebElement calculatePrice1 = driver.findElement(By.xpath("//a[normalize-space()='Hitung Harga']"));

        String calc = calculatePrice1.getText();
        System.out.println(calc);
        wait.until(ExpectedConditions.elementToBeClickable(calculatePrice)).click();
    }
}
