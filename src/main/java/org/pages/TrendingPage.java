package org.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class TrendingPage {

    private AppiumDriver driver;

    private int trendingListSize = 0;
    private String trendingTitle;
    private Random rand = new Random();

    //Page Locators
    private By trendingTitleSection = By.xpath("//span[text()='Argentina trends']");
    private By trendingList = By.xpath("(//div[@data-testid='trend'])");

    public TrendingPage(AppiumDriver driver) {
        this.driver = driver;
    }

    //Elements Actions
    public boolean VerifyIfTheTrendingTitleIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(trendingTitleSection));
        return driver.findElement(trendingTitleSection).isDisplayed();
    }

    public void scrollDownOnTrendingList() {

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfElementLocated(trendingList));
        trendingListSize = driver.findElements(trendingList).size();

        int scrollToTrendingRandom = rand.nextInt(trendingListSize - 15 + 1) + 15;
        String aleatoryTrendingOnTrendingList = String.format("//div[@data-testid='cellInnerDiv'][%s]", scrollToTrendingRandom);

        Actions actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(By.xpath(aleatoryTrendingOnTrendingList))).perform();
    }

    public void selectATrendingAleatoryOnTheTrendingList() {

        int trendingRandom = rand.nextInt(trendingListSize - 3 + 1) + 3;

        String aleatoryTrendingLocator = String.format("(//div[@data-testid='trend'])[%s]", trendingRandom);
        String aleatoryTrendingTitle = String.format("(//div[@data-testid='trend'])[%s]/div/div[2]/span", trendingRandom);

        Actions actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(By.xpath(aleatoryTrendingLocator))).perform();
        actions.scrollByAmount(driver.manage().window().getPosition().getX(), driver.manage().window().getPosition().getY() + 50).perform();

        trendingTitle = driver.findElement(By.xpath(aleatoryTrendingTitle)).getText();
        driver.findElement(By.xpath(aleatoryTrendingTitle)).click();
    }

    public String getAleatoryTrendingTitleSelected() {
        return trendingTitle;
    }
}
