package org.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserNamePage {

    private AppiumDriver driver;

    //Page Locators
    private By sigInMessageBar = By.xpath("//span[text()='Sign in to X']");
    private By userNameField = By.cssSelector("[autocomplete='username']");
    private By nextButton = By.xpath("//span[text()='Next']");
    private By alertWrongUserToast = By.xpath("//div[@data-testid='toast']//span[contains(text(),'could not find your account')]");

    public UserNamePage(AppiumDriver driver) {
        this.driver = driver;
    }

    //Elements Actions
    public boolean verifyIfTheSignInMessageBarIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(sigInMessageBar));
        return driver.findElement(sigInMessageBar).isDisplayed();
    }

    public void enterTextOnUsernameField(String username) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(alertWrongUserToast));
        driver.findElement(userNameField).sendKeys(username);
    }

    public void clickOnNextButton() {
        driver.findElement(nextButton).click();
    }

    public boolean verifyIfTheWrongUserToastIsDisplayed(){
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(alertWrongUserToast));
        return driver.findElement(alertWrongUserToast).isDisplayed();

    }


}
