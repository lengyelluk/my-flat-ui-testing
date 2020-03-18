package com.lengyel.tests;

import com.lengyel.actions.AssertActions;
import com.lengyel.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FindRoomTest {

    WebDriver driver;
    FindRoomListPage findRoomListPage;
    FindRoomDetailPage findRoomDetailPage;

    @BeforeTest
    public void setup () {
        System.setProperty("webdriver.chrome.driver", "src/main/java/com/lengyel/vendor/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void findRoom () {
        findRoomListPage = new FindRoomListPage(driver);
        findRoomDetailPage = findRoomListPage.openSpecificRoom("Best flat ever");
        findRoomDetailPage.checkRoomDetails();
        AssertActions.checkForVerificationErrors();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
