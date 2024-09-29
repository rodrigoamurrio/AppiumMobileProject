package org.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private AppiumDriver driver;

    //Page Locators
    private By closeButtonBar = By.cssSelector("[data-testid='xMigrationBottomBar']");
    private By explorerOption = By.cssSelector("[data-testid='AppTabBar_Explore_Link']");
    private By userAvatarOption = By.cssSelector("[data-testid='DashButton_ProfileIcon_Link']");
    private By buttonsBar = By.cssSelector("[data-testid='BottomBar']");

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
    }

    //Elements Actions
    public void clickOnCloseButtonBar() {
        driver.findElement(closeButtonBar).click();
    }

    public boolean verifyIfTheUserAvatarIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(userAvatarOption));
        return driver.findElement(userAvatarOption).isDisplayed();
    }

    public boolean verifyIfTheOptionBarIsDisplayed() {
        return driver.findElement(buttonsBar).isDisplayed();
    }

    public void clickOnTheSearchOption() {
        driver.findElement(explorerOption).click();
    }
}
