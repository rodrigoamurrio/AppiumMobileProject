package org.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class TrendPage {

    private AppiumDriver driver;

    private Random rand = new Random();
    private String tweetText;

    //Page Locators
    private By topOptionOfTabList = By.xpath("//span[text()='Top']");
    private By trendsList = By.xpath("(//article[@data-testid='tweet'])");


    public TrendPage(AppiumDriver driver) {
        this.driver = driver;
    }

    //Elements Actions
    public boolean VerifyIfTheTopOptionOfTabListIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(topOptionOfTabList));
        return driver.findElement(topOptionOfTabList).isDisplayed();
    }

    public boolean verifyIfTrendingTitleIsDisplayedOnTheSearchBox(String trendingTitleSelected) {

        By searchBoxValue = By.xpath(String.format("//input[contains(@value,'%s')]", trendingTitleSelected));
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(searchBoxValue));
        return driver.findElement(searchBoxValue).isDisplayed();
    }

    public void selectAnAleatoryTweetOnTheTweetList() {

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(trendsList));

        int listSize = driver.findElements(trendsList).size();
        int random = rand.nextInt(listSize - 1 + 1) + 1;

        String aleatoryTweetText = String.format("(//article[@data-testid='tweet'])[%s]//div[@data-testid='tweetText']", random);

        Actions actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(By.xpath(aleatoryTweetText))).perform();
        tweetText = driver.findElement(By.xpath(aleatoryTweetText)).getText();
        actions.scrollByAmount(driver.manage().window().getPosition().getX(), driver.manage().window().getPosition().getY() + 200).perform();

        driver.findElement(By.xpath(aleatoryTweetText)).click();
    }

    public String getTweetTextOfTheTweetSelected() {
        return tweetText;
    }
}
