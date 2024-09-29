package org.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PostPage {

    private AppiumDriver driver;

    //Page Locators
    private By postTitle = By.xpath("//span[text()='Post']");
    private By postText = By.xpath("//div[@data-testid='tweetText']/span");
    private By likePostButton = By.xpath("//button[@data-testid='like'][1]");
    private By likedPostButtonColor = By.xpath("//button[@data-testid='unlike'][1]/div");
    private By unlikePostButton = By.xpath("//button[@data-testid='unlike'][1]");

    public PostPage(AppiumDriver driver) {
        this.driver = driver;
    }

    //Elements Actions
    public boolean verifyIfThePostTitleIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(postTitle));
        return driver.findElement(postTitle).isDisplayed();
    }

    public String getPostText() {
        return driver.findElement(postText).getText();
    }

    public void clickOnTheLikeButtonOfThePost() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(likePostButton));
        Actions actions = new Actions(driver);
        actions.scrollByAmount(driver.manage().window().getPosition().getX(), driver.manage().window().getPosition().getY() + 450).perform();
        driver.findElement(likePostButton).click();
    }

    public String getStyleColorOfTheLikeButton() {
        return driver.findElement(likedPostButtonColor).getAttribute("style");
    }

    public void clickOnTheUnlikeButtonOfThePost() {
        driver.findElement(unlikePostButton).click();
    }


}
