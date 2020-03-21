package com.lengyel.tests;

import com.browserstack.local.Local;
import com.lengyel.actions.AssertActions;
import com.lengyel.pages.*;

import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.lengyel.constants.Constants;
import com.lengyel.drivers.BrowserCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;


@Listeners(com.lengyel.listener.TestNGListener.class)
public class LoginTest {

    WebDriver driver;
    SessionId sessionId;
    MainPage mainPage;
    LoginPage loginPage;
    AuthenticatedMainPage authenticatedMainPage;
    DesiredCapabilities caps;

    @BeforeTest
    public void setup () throws Exception {
        BrowserCapabilities bc = new BrowserCapabilities();
        caps = bc.buildDesiredCapabilities();
        caps.setCapability("name", "Login Test");
        driver = new RemoteWebDriver(
            new URL(Constants.URL),
            caps
        );
        sessionId = ((RemoteWebDriver)driver).getSessionId();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void findRoom (ITestContext context) throws Exception {
        context.setAttribute("sessionId", sessionId.toString());
        String email = "test@test.com";
        String password = "testtest";
        mainPage = new MainPage(driver);
        loginPage = mainPage.goToLoginPage();
        authenticatedMainPage = loginPage.loginUser(email, password);
        authenticatedMainPage.checkUserLoggedIn();
        //AssertActions.checkForVerificationErrors(sessionId);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
