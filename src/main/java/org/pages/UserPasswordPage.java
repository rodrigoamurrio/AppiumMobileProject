package org.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserPasswordPage {

    private AppiumDriver driver;

    //Page Locators
    private By enterPassswordMessageBar = By.xpath("//span[text()='Enter your password']");
    private By passwordField = By.cssSelector("[autocomplete='current-password']");
    private By loginButton = By.cssSelector("[data-testid='LoginForm_Login_Button']");
    private By alertWrongPasswordToast = By.xpath("//div[@data-testid='toast']//span[contains(text(),'Wrong password')]");

    public UserPasswordPage(AppiumDriver driver) {
        this.driver = driver;
    }

    //Elements Actions
    public boolean verifyIfTheEnterPasswordMessageBarIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(enterPassswordMessageBar));
        return driver.findElement(enterPassswordMessageBar).isDisplayed();
    }

    public void enterTextOnPasswordField(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(alertWrongPasswordToast));
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean verifyIfTheWrongPasswordToastIsDisplayed(){
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(alertWrongPasswordToast));
        return driver.findElement(alertWrongPasswordToast).isDisplayed();
    }

}
