package com.lengyel.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page {

    @FindBy(how = How.XPATH, using="//input[@name='email']")
    private WebElement emailField;

    @FindBy(how = How.XPATH, using="//input[@name='password']")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using="//button[text()='Login']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super("LoginPage", "https://new-my-flat-app.herokuapp.com/login", driver);

        PageFactory.initElements(driver, this);
        driver().get(url());

        if(isDisplayed() == false) {
            throw new RuntimeException("LoginPage is not displayed!");
        }
    }


    public AuthenticatedMainPage loginUser(String email, String password) {
        driverWait().until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
        return new AuthenticatedMainPage(driver());
    }


}