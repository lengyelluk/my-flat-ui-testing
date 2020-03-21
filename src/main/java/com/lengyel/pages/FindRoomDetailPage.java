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

public class FindRoomDetailPage extends Page {

    @FindBy(how = How.XPATH, using="(//h3[@class='heading']/following-sibling::p)[1]")
    private WebElement priceCheck;

    @FindBy(how = How.XPATH, using="(//h3[@class='heading']/following-sibling::p)[2]")
    private WebElement locationCheck;

    @FindBy(how = How.XPATH, using="(//h3[@class='heading']/following-sibling::p)[3]")
    private WebElement flatmatesCheck;

    @FindBy(how = How.XPATH, using="(//h3[@class='heading']/following-sibling::p)[4]")
    private WebElement prefFlatmatesCheck;

    @FindBy(how = How.XPATH, using="(//h3[@class='heading']/following-sibling::p)[5]")
    private WebElement availabilityCheck;

    @FindBy(how = How.XPATH, using="//div[@class='inline']/span[1]")
    private WebElement smokingAllowedCheck;

    @FindBy(how = How.XPATH, using="//div[@class='inline']/span[2]")
    private WebElement petAllowedCheck;

    public FindRoomDetailPage(WebDriver driver) {
        super("FindRoomDetailPage", "https://new-my-flat-app.herokuapp.com/cards", driver);

        PageFactory.initElements(driver, this);
    }

    public void checkRoomDetails() {
        String price = "350 EUR/month";
        String street = "Sancova";
        String district = "Staré Mesto";
        String menFlatmate = "1 males";
        String womenFlatmate = "1 females";
        String prefFlatmates = "Males only";
        String availability = "Available from 2018-01-01 with minimal stay 5 months";
        String smokingAllowed = "Smoking Allowed";
        String petAllowed = "Pet Allowed";

        try {
        Thread.sleep(2000);
        } catch (Exception e) {
            logger.info("waiting for flat details to load");
        }
        driverWait().until(ExpectedConditions.visibilityOf(priceCheck));
        
        String priceCheckValue = priceCheck.getText();
        String locationCheckValue = locationCheck.getText();
        String flatmatesCheckValue = flatmatesCheck.getText();
        String prefFlatmatesCheckValue = prefFlatmatesCheck.getText();
        String availabilityCheckValue = availabilityCheck.getText();
        String smokingAllowedCheckValue = smokingAllowedCheck.getText();
        String petAllowedCheckValue = petAllowedCheck.getText();

        logger.info(priceCheckValue);
        logger.info(locationCheckValue);
        logger.info(flatmatesCheckValue);
        logger.info(prefFlatmatesCheckValue);
        logger.info(availabilityCheckValue);
        logger.info(smokingAllowedCheckValue);
        logger.info(petAllowedCheckValue);

        assertActions().assertEquals(priceCheckValue, price);
        /*assertActions().assertEquals(location, locationCheckValue);
        assertActions().assertEquals(price, priceCheckValue);
        assertActions().assertEquals(price, priceCheckValue);
        assertActions().assertEquals(price, priceCheckValue);
        assertActions().assertEquals(price, priceCheckValue);
        assertActions().assertEquals(price, priceCheckValue);*/
    }
}