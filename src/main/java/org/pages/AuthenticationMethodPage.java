package org.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthenticationMethodPage {

    private AppiumDriver driver;

    //Page Locators
    private By initialMessageBar = By.xpath("//span[text()='Happening now']");
    private By signInButton = By.cssSelector("[data-testid='loginButton']");
    private By closeButtonBar = By.cssSelector("[data-testid='xMigrationBottomBar']");

    public AuthenticationMethodPage(AppiumDriver driver) {
        this.driver = driver;
    }

    //Elements Actions
    public boolean verifyIfInitialMessageIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(initialMessageBar));
        return driver.findElement(initialMessageBar).isDisplayed();
    }

    public void clickOnSignInButton() {
        driver.findElement(signInButton).click();
    }

    public void clickOnCloseButtonBar() {
        driver.findElement(closeButtonBar).click();
    }
}
