package test;

import io.qameta.allure.*;
import org.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.constants.Constants.*;

public class TwitterXTest extends BaseTest {

    //Pages
    AuthenticationMethodPage authenticationMethodPage;
    UserNamePage userNamePage;
    UserPasswordPage userPasswordPage;
    HomePage homePage;
    ExplorerPage explorerPage;
    TrendingPage trendingPage;
    TrendPage trendPage;
    PostPage postPage;

    @Test
    @Epic("Tweet Functionalities")
    @Feature("Login application")
    @Story("Login validations")
    @Description("Login with invalid username and invalid password, verify that the warning message is displayed")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Rodrigo Amurrio")
    public void loginWithInvalidUserNameOrPasswordTest() throws Exception {

        authenticationMethodPage = new AuthenticationMethodPage(driver);
        userNamePage = new UserNamePage(driver);
        userPasswordPage = new UserPasswordPage(driver);

        Assert.assertTrue(authenticationMethodPage.verifyIfInitialMessageIsDisplayed(), "The \"Happening now\" message is not displayed on the start page");
        Allure.step("Open X Apllication", () -> {
            takeSnapShot(driver);
        });
        authenticationMethodPage.clickOnCloseButtonBar();
        authenticationMethodPage.clickOnSignInButton();
        Allure.step("Go to Sig In page");

        Assert.assertTrue(userNamePage.verifyIfTheSignInMessageBarIsDisplayed(), "The \"Sign in to X\" message is not displayed on the sign in page");
        Allure.step("Enter invalid username and click on the Next button");
        userNamePage.enterTextOnUsernameField(INVALID_USER_NAME);
        userNamePage.clickOnNextButton();
        Assert.assertTrue(userNamePage.verifyIfTheWrongUserToastIsDisplayed(), "The wrong user toast alert is not displayed");
        Allure.step("The authentication validation is displayed", () -> {
            takeSnapShot(driver);
        });

        userNamePage.enterTextOnUsernameField(USER_NAME);
        userNamePage.clickOnNextButton();

        Allure.step("Go to Password page");
        Assert.assertTrue(userPasswordPage.verifyIfTheEnterPasswordMessageBarIsDisplayed(), "The \"Enter your password\" message is not displayed on the password page");
        userPasswordPage.enterTextOnPasswordField(INVALID_PASSWORD);
        userPasswordPage.clickOnLoginButton();
        Allure.step("Enter invalid password and click on the Login button");
        Assert.assertTrue(userPasswordPage.verifyIfTheWrongPasswordToastIsDisplayed(), "The wrong password toast alert is not displayed");
        Allure.step("The authentication validation is displayed", () -> {
            takeSnapShot(driver);
        });
    }

    @Test
    @Epic("Tweet Functionalities")
    @Feature("Tweet actions")
    @Story("Like a specific tweet")
    @Description("Select an aleatory tweet of trending section and liked it")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Rodrigo Amurrio")
    public void selectAndLikeAnAleatoryTweetTest() {

        authenticationMethodPage = new AuthenticationMethodPage(driver);
        userNamePage = new UserNamePage(driver);
        userPasswordPage = new UserPasswordPage(driver);
        homePage = new HomePage(driver);
        explorerPage = new ExplorerPage(driver);
        trendingPage = new TrendingPage(driver);
        trendPage = new TrendPage(driver);
        postPage = new PostPage(driver);

        Assert.assertTrue(authenticationMethodPage.verifyIfInitialMessageIsDisplayed(), "The \"Happening now\" message is not displayed on the start page");
        Allure.step("Open X Apllication", () -> {
            takeSnapShot(driver);
        });
        authenticationMethodPage.clickOnCloseButtonBar();
        authenticationMethodPage.clickOnSignInButton();
        Allure.step("Go to Sign in page");

        Assert.assertTrue(userNamePage.verifyIfTheSignInMessageBarIsDisplayed(), "The \"Sign in to X\" message is not displayed on the sign in page");
        userNamePage.enterTextOnUsernameField(USER_NAME);
        userNamePage.clickOnNextButton();
        Allure.step("Enter a valid user and go to password section");

        Assert.assertTrue(userPasswordPage.verifyIfTheEnterPasswordMessageBarIsDisplayed(), "The \"Enter your password\" message is not displayed on the password page");
        userPasswordPage.enterTextOnPasswordField(USER_PASSWORD);
        userPasswordPage.clickOnLoginButton();
        Allure.step("Enter a valid password and go to home page");


        Assert.assertTrue(homePage.verifyIfTheUserAvatarIsDisplayed(), "The user avatar is not displayed on the home page");
        Allure.step("Home page is displayed", () -> {
            takeSnapShot(driver);
        });
        homePage.clickOnCloseButtonBar();
        Assert.assertTrue(homePage.verifyIfTheOptionBarIsDisplayed(), "The option bar is not displayed on the home page");
        homePage.clickOnTheSearchOption();
        Allure.step("Go to Explorer page");

        Assert.assertTrue(explorerPage.verifyIfSearchBoxFieldIsDisplayed(), "The search box is not displayed on the explorer page");
        Assert.assertTrue(explorerPage.verifyIfSearchOptionListIsDisplayed(), "The search option list is not displayed on the explorer page");
        Allure.step("Explorer page is open", () -> {
            takeSnapShot(driver);
        });
        explorerPage.clickOnTrendingOption();
        Allure.step("Go to Trending option");
        Assert.assertTrue(trendingPage.VerifyIfTheTrendingTitleIsDisplayed(), "The trending title of the user is not displayed on the trending tab");
        Allure.step("Trending page is open", () -> {
            takeSnapShot(driver);
        });
        trendingPage.scrollDownOnTrendingList();
        Allure.step("Scroll down on trending list");
        trendingPage.selectATrendingAleatoryOnTheTrendingList();
        Allure.step("Select an aleatory trending ", () -> {
            takeSnapShot(driver);
        });

        Assert.assertTrue(trendPage.VerifyIfTheTopOptionOfTabListIsDisplayed(), "The top tap is not displayed on the trend page");
        Assert.assertTrue(trendPage.verifyIfTrendingTitleIsDisplayedOnTheSearchBox(trendingPage.getAleatoryTrendingTitleSelected()), "The trending title selected is not displayed on the search box on the trend page");
        trendPage.selectAnAleatoryTweetOnTheTweetList();
        Allure.step("Select an aleatory trend", () -> {
            takeSnapShot(driver);
        });

        Assert.assertTrue(postPage.verifyIfThePostTitleIsDisplayed(), "The top title is not displayed when open a tweet on the post page ");
        Assert.assertTrue(trendPage.getTweetTextOfTheTweetSelected().contains(postPage.getPostText()), "The tweet text selected on the trend page does not contains the tweet text on the tweet selected");
        Allure.step("Tweet selected is open", () -> {
            takeSnapShot(driver);
        });
        postPage.clickOnTheLikeButtonOfThePost();
        Assert.assertTrue(postPage.getStyleColorOfTheLikeButton().contains(LIKED_COLOR), "The color of the liked option is not red");
        Allure.step("Like tweet is displayed", () -> {
            takeSnapShot(driver);
        });

        postPage.clickOnTheUnlikeButtonOfThePost();
    }
}
