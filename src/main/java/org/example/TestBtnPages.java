package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBtnPages {
    WebDriver driver;
    WebDriverWait wait;

    public TestBtnPages(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 100);
    }

    public void drpDwnValas(){
        //span[@class='btn-switch-text']
        By inputUsername = By.xpath("/html/body/div[1]/header/div[2]/div/div/div[1]/div[3]");
        WebElement e = driver.findElement(inputUsername);
        Actions actions = new Actions(driver);
        // Lakukan operasi hover mouse
        actions.moveToElement(e).perform();
        // wait.until(ExpectedConditions.elementToBeClickable(inputUsername)).click();
    }

    public void clickConverterKurs(){
        //a[normalize-space()='e-kurs konverter kalkulator']
        By click = By.xpath("//a[normalize-space()='e-kurs konverter kalkulator']");
        wait.until(ExpectedConditions.elementToBeClickable(click)).click();
    }

    public void inptNominal(String usd){
        By click = By.xpath("//input[@id='SourceNumber']");
        wait.until(ExpectedConditions.elementToBeClickable(click)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(click)).sendKeys(usd);
    }

    public void selectCurrentCode(){
        By click = By.xpath("(//select[@id='CurrencyCode'])[2]");
        wait.until(ExpectedConditions.elementToBeClickable(click)).click();
        Select btn = new Select(driver.findElement(By.xpath("(//select[@id='CurrencyCode'])[2]")));
        btn.selectByVisibleText("IDR");
        btn.selectByIndex(7);
    }

    public void chooseCurentCode() {
        By channel = By.xpath(" (//select[@aria-invalid='false'])[2]");
        wait.until(ExpectedConditions.elementToBeClickable(channel)).click();
        Select btn = new Select(driver.findElement(By.xpath("(//select[@aria-invalid='false'])[2]")));
        btn.selectByVisibleText("URI");
        btn.selectByIndex(0);
    }

    public void clickHitung(){
        By click = By.xpath(" //input[@value='hitung']");
        wait.until(ExpectedConditions.elementToBeClickable(click)).click();
    }

    public void verifyHasil(){
        WebElement currentUSD = driver.findElement(By.xpath("//td[normalize-space()='15.690,00']"));
        WebElement inptUSD = driver.findElement(By.xpath("//input[@id='SourceNumber']"));
        WebElement result = driver.findElement(By.xpath("//input[@id='Result']"));

        String USD = currentUSD.getText();
        String amount = inptUSD.getText();
        String resultUSDtoIDR = result.getText();

        int convertUSD = Integer.parseInt(USD);
        int convertUSDtoIDR = Integer.parseInt(amount);
        int resultConvert = Integer.parseInt(resultUSDtoIDR);

        int calculation = convertUSD * convertUSDtoIDR;

        if (calculation == resultConvert) {
            System.out.println("Convert Sesuai");
            System.out.println("Hasil Convert = " + result);
        } else {
            System.out.println("Convert Tidak Sesuai");
            System.out.println("Hasil Convert = " + result);
        }
    }

    public void verifyValasInformation(){
        WebElement element = driver.findElement(By.xpath("//h2[normalize-space()='Informasi Kurs Bank BTN (Valas)']"));
        String verifyText = element.getText();
        String expectedText = "Informasi Kurs Bank BTN (Valas)";
        if (verifyText.equals(expectedText)){
            System.out.println("Element Exist");
        }
    }
}
