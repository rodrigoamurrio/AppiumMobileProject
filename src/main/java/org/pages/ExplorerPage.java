package org.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplorerPage {

    private AppiumDriver driver;

    //Page Locators
    private By searchBoxField = By.cssSelector("[data-testid='SearchBox_Search_Input']");
    private By searchOptionList = By.cssSelector("[data-testid='ScrollSnap-List']");
    private By trendingOption = By.xpath("//span[text()='Trending']");

    public ExplorerPage(AppiumDriver driver) {
        this.driver = driver;
    }

    //Elements Actions
    public boolean verifyIfSearchBoxFieldIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(searchBoxField));
        return driver.findElement(searchBoxField).isDisplayed();
    }

    public boolean verifyIfSearchOptionListIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(searchOptionList));
        return driver.findElement(searchOptionList).isDisplayed();
    }

    public void clickOnTrendingOption() {
        driver.findElement(trendingOption).click();
    }
}
