package com.lengyel.tests;

import com.lengyel.actions.AssertActions;
import com.lengyel.constants.Constants;
import com.lengyel.drivers.BrowserCapabilities;
import com.lengyel.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.lengyel.listener.TestNGListener;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Listeners(com.lengyel.listener.TestNGListener.class)
public class RentOutRoomTest {

    WebDriver driver;
    SessionId sessionId;
    MainPage mainPage;
    LoginPage loginPage;
    AuthenticatedMainPage authenticatedMainPage;
    RentOutRoomPage rentOutRoomPage;
    DesiredCapabilities caps;

    @BeforeTest
    public void setup () throws Exception {
        final BrowserCapabilities bc = new BrowserCapabilities();
        caps = bc.buildDesiredCapabilities();
        caps.setCapability("name", "Rent Out Room Test");
        driver = new RemoteWebDriver(
            new URL(Constants.URL),
            caps
        );
        sessionId = ((RemoteWebDriver)driver).getSessionId();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @Test
    public void rentOutRoom (ITestContext context) throws Exception {
        context.setAttribute("sessionId", sessionId.toString());
        String email = "test@test.com";
        String password = "testtest";
        mainPage = new MainPage(driver);
        loginPage = mainPage.goToLoginPage();
        authenticatedMainPage = loginPage.loginUser(email, password);
        authenticatedMainPage.checkUserLoggedIn();
        authenticatedMainPage.goToHomePage();
        rentOutRoomPage = authenticatedMainPage.goToRentOutRoomPage();
        rentOutRoomPage.setFlatDetails();
        //AssertActions.checkForVerificationErrors(sessionId);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
