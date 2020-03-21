package com.lengyel.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FindRoomListPage extends Page {

    @FindBy(how = How.XPATH, using="(//div[@class='grid']//h3)[1]")
    private WebElement firstCard;


    public FindRoomListPage(WebDriver driver) {
        super("FindRoomListPage", "https://new-my-flat-app.herokuapp.com/flatList", driver);

        PageFactory.initElements(driver, this);
        driver().get(url());

        if(isDisplayed() == false) {
            throw new RuntimeException("FindRoomListPage is not displayed!");
        }
    }

    public FindRoomDetailPage openSpecificRoom(String roomTitle) {
        By roomLocator = By.xpath("//div[@class='grid']//h3[text()='" + roomTitle + "']");
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(roomLocator));
        driver().findElement(roomLocator).click();
        return new FindRoomDetailPage(driver());
    }

    
}