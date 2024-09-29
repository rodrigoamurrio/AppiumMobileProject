package test;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.utils.ReadProperties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {

    public AppiumDriver driver;

    private ReadProperties readProperties = new ReadProperties();

    @BeforeMethod
    public void setUp() throws IOException {

        Path confDir = Paths.get(new File("target/images").getAbsolutePath());
        Files.createDirectories(confDir);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName",readProperties.getDeviceName());
        cap.setCapability("udui",readProperties.getDeviceUdUi());
        cap.setCapability("platformName",readProperties.getDevicePlatformName());
        cap.setCapability("platformVersion",readProperties.getDevicePlatformVersion());
        cap.setCapability("browserName", "Chrome");
        cap.setCapability("chromedriverExecutable",readProperties.getChromeDriverPath().getAbsolutePath());
        cap.setCapability("ignoreHiddenApiPolicyError",true);
        cap.setCapability("noReset", true);

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver(url, cap);
        driver.get(readProperties.getUrlApplication());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public static void takeSnapShot(AppiumDriver webdriver) throws Exception{
        int numberRandom = (int)(Math.random() * 9000) + 1000;
        String fileName = "image_"+numberRandom+".png";
        String filePath = "target/images/"+fileName;

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(new File(filePath).getAbsolutePath());
        Files.copy(SrcFile.toPath(), DestFile.toPath());

        try (InputStream is = Files.newInputStream(Paths.get(filePath))) {
             Allure.attachment(fileName, is);
        }
    }
}
