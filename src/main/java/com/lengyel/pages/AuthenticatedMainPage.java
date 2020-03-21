package com.lengyel.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticatedMainPage extends Page {

    @FindBy(how = How.XPATH, using="//div[contains(text(), 'Welcome Test')]")
    private WebElement welcomeMsg;

    @FindBy(how = How.XPATH, using="//img[contains(@src,'rentOutRoom')]")
    private WebElement rentOutRoomLink;

    @FindBy(how = How.XPATH, using="//img[contains(@src,'findRoom')]")
    private WebElement findRoomLink;


    public AuthenticatedMainPage(WebDriver driver) {
        super("AuthenticatedMainPage", "https://new-my-flat-app.herokuapp.com/", driver);

        PageFactory.initElements(driver, this);
        //driver().get(url());

        if(isDisplayed() == false) {
            throw new RuntimeException("AuthenticatedMainPage is not displayed!");
        }
    }

    public void checkUserLoggedIn() {
        boolean welcomeMsgDisplayed = false;
        try {
            driverWait().until(ExpectedConditions.visibilityOf(welcomeMsg));
            welcomeMsgDisplayed = true;
        } catch (TimeoutException e) {
            logger.info("Welcome message not displayed");
        }
        assertActions().assertTrue(welcomeMsgDisplayed, "User logged in");
    }

    public RentOutRoomPage goToRentOutRoomPage() {
        driverWait().until(ExpectedConditions.visibilityOf(rentOutRoomLink));
        rentOutRoomLink.click();
        return new RentOutRoomPage(driver());        
    }

    public FindRoomListPage goToFindRoomPage() {
        driverWait().until(ExpectedConditions.visibilityOf(findRoomLink));
        findRoomLink.click();
        return new FindRoomListPage(driver());        
    }
}