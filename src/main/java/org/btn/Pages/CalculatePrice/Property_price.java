package org.btn.Pages.CalculatePrice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Property_price {
    WebDriver driver;
    WebDriverWait wait;

    public Property_price(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 100);
    }

    public void total_income(String income){
        By totalIncome = By.xpath("//input[@placeholder='Penghasilan Total']");
        wait.until(ExpectedConditions.elementToBeClickable(totalIncome)).sendKeys(income);
    }

    public void expenditure(String outcome){
        By totalIncome = By.xpath("//input[@placeholder='Pengeluaran']");
        wait.until(ExpectedConditions.elementToBeClickable(totalIncome)).sendKeys(outcome);
    }

    public void select_time(){
        By time = By.xpath("//input[@placeholder='Pengeluaran']");
        wait.until(ExpectedConditions.elementToBeClickable(time)).click();
    }

    public void calculate(){
        By calc = By.xpath("//button[normalize-space()='Hitung']");
        wait.until(ExpectedConditions.elementToBeClickable(calc)).click();
    }

    public String get_calculation_results(){
        WebElement result = driver.findElement(By.xpath("//div[@id='harga_hasil']//h3"));
        return result.getText();
    }
}
