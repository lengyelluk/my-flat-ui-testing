package com.lengyel.pages;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RentOutRoomPage extends Page {

    @FindBy(how = How.XPATH, using="//select")
    private WebElement districtDropdown;

    @FindBy(how = How.XPATH, using="//input[@placeholder='Street name']")
    private WebElement streetField;

    @FindBy(how = How.XPATH, using="//input[@placeholder='Title']")
    private WebElement titleField;
    
    @FindBy(how = How.XPATH, using="//button[text()='Save and Continue']")
    private WebElement saveContinueButton;

    @FindBy(how = How.XPATH, using="//label[text()='Number of men living in the flat']//following-sibling::select")
    private WebElement menDropdown;

    @FindBy(how = How.XPATH, using="//label[text()='Number of men living in the flat']//following-sibling::select/option[2]")
    private WebElement menDropdown1;

    @FindBy(how = How.XPATH, using="//label[text()='Male']/input")
    private WebElement maleCheckbox;

    @FindBy(how = How.XPATH, using="//label[text()='Female']/input")
    private WebElement femaleCheckbox;

    @FindBy(how = How.XPATH, using="//label[text()='Couple']/input")
    private WebElement coupleCheckbox;

    @FindBy(how = How.XPATH, using="//label[text()='Pet Allowed']/input")
    private WebElement petAllowedCheckbox;

    @FindBy(how = How.XPATH, using="//label[text()='Smoking Allowed']/input")
    private WebElement smokingAllowedCheckbox;
    
    @FindBy(how = How.XPATH, using="//input[@placeholder='300 EUR']")
    private WebElement priceField;
    
    @FindBy(how = How.XPATH, using="//input[@placeholder='5 months']")
    private WebElement durationField;

    @FindBy(how = How.XPATH, using="//input[@name='availabilityDate']")
    private WebElement availabilityDateField;

    @FindBy(how = How.XPATH, using="(//div[@role='list']/div[@role='listitem']/div[@class='content'])[1]")
    private WebElement districtCheck;

    @FindBy(how = How.XPATH, using="(//div[@role='list']/div[@role='listitem']/div[@class='content'])[2]")
    private WebElement streetCheck;

    @FindBy(how = How.XPATH, using="(//div[@role='list']/div[@role='listitem']/div[@class='content'])[3]")
    private WebElement maleFlatmatesCheck;

    @FindBy(how = How.XPATH, using="(//div[@role='list']/div[@role='listitem']/div[@class='content'])[4]")
    private WebElement femaleFlatmatesCheck;

    @FindBy(how = How.XPATH, using="(//div[@role='list']/div[@role='listitem']/div[@class='content'])[5]")
    private WebElement prefMaleFlatmatesCheck;

    @FindBy(how = How.XPATH, using="(//div[@role='list']/div[@role='listitem']/div[@class='content'])[6]")
    private WebElement prefFemaleFlatmatesCheck;

    @FindBy(how = How.XPATH, using="(//div[@role='list']/div[@role='listitem']/div[@class='content'])[7]")
    private WebElement prefCoupleFlatmatesCheck;

    @FindBy(how = How.XPATH, using="(//div[@role='list']/div[@role='listitem']/div[@class='content'])[8]")
    private WebElement petAllowedCheck;

    @FindBy(how = How.XPATH, using="(//div[@role='list']/div[@role='listitem']/div[@class='content'])[9]")
    private WebElement smokingAllowedCheck;

    @FindBy(how = How.XPATH, using="(//div[@role='list']/div[@role='listitem']/div[@class='content'])[10]")
    private WebElement priceCheck;

    @FindBy(how = How.XPATH, using="(//div[@role='list']/div[@role='listitem']/div[@class='content'])[11]")
    private WebElement minStayCheck;

    @FindBy(how = How.XPATH, using="(//div[@role='list']/div[@role='listitem']/div[@class='content'])[12]")
    private WebElement availabiliyDateCheck;

    @FindBy(how = How.XPATH, using="//button[text()='Confirm']")
    private WebElement confirmButton;

    @FindBy(how = How.XPATH, using="//h1[text()='Room to rent successfully saved']")
    private WebElement successMsg;
    

    public RentOutRoomPage(WebDriver driver) {
        super("RentOutRoomPage", "https://new-my-flat-app.herokuapp.com/addFlat", driver);

        PageFactory.initElements(driver, this);
        //driver().get(url());

        if(isDisplayed() == false) {
            throw new RuntimeException("RentOutRoomPage is not displayed!");
        }
    }

    public void setFlatDetails() {
        String district = "Ruzinov";
        String street = "Test street name";
        String title = "Test title";
        String menFlatmate = "1";
        String womenFlatmate = "2";
        String yesOption = "Yes";
        String noOption = "No";
        String price = "400";
        String minStay = "12";
        String availabilityDate = "01-05-2020";

        //district
        Select districtDropdown = new Select(driver().findElement(By.tagName("select")));
        districtDropdown.selectByValue(district);
        //street 
        streetField.sendKeys(street);
        //title
        titleField.sendKeys(title);
        //button
        saveContinueButton.click();
       
        //2nd part
        //men flatmate dropdown
        By menDropdownLocator = By.xpath("//label[text()='Number of men living in the flat']//following-sibling::select");
        Select menFlatmateDropdown = new Select(driver().findElement(menDropdownLocator));
        menFlatmateDropdown.selectByIndex(Integer.valueOf(menFlatmate));
        //women flatmate dropdown
        By womenDropdownLocator = By.xpath("//label[text()='Number of women living in the flat']//following-sibling::select");
        Select womenFlatmateDropdown = new Select(driver().findElement(womenDropdownLocator));
        womenFlatmateDropdown.selectByIndex(Integer.valueOf(womenFlatmate));
        //button
        saveContinueButton.click();        

        //3rd part
        maleCheckbox.click();
        femaleCheckbox.click();
        coupleCheckbox.click();
        petAllowedCheckbox.click();
        smokingAllowedCheckbox.click();
        //button
        saveContinueButton.click();

        //4th part
        priceField.sendKeys(price);
        durationField.sendKeys(minStay);
        availabilityDateField.sendKeys(availabilityDate);
        //button
        saveContinueButton.click();

        //summary
        String districtCheckValue = StringUtils.substringAfterLast(districtCheck.getText(), ":").trim();
        String streetCheckValue = StringUtils.substringAfterLast(streetCheck.getText(), ":").trim();
        String maleFlatmatesValue = StringUtils.substringAfterLast(maleFlatmatesCheck.getText(), ":").trim();
        String femaleFlatmatesValue = StringUtils.substringAfterLast(femaleFlatmatesCheck.getText(), ":").trim();
        String prefMaleFlatmatesValue = StringUtils.substringAfterLast(prefMaleFlatmatesCheck.getText(), ":").trim();
        String prefFemaleFlatmatesValue = StringUtils.substringAfterLast(prefFemaleFlatmatesCheck.getText(), ":").trim();
        String prefCoupleFlatmatesValue = StringUtils.substringAfterLast(prefCoupleFlatmatesCheck.getText(), ":").trim();
        String petAllowedValue = StringUtils.substringAfterLast(petAllowedCheck.getText(), ":").trim();
        String smokingAllowedValue = StringUtils.substringAfterLast(smokingAllowedCheck.getText(), ":").trim();
        String priceValue = StringUtils.substringAfterLast(priceCheck.getText(), ":").trim();
        String minStayValue = StringUtils.substringAfterLast(minStayCheck.getText(), ":").trim();
        String availabilityDateValue = StringUtils.substringAfterLast(availabiliyDateCheck.getText(), ":").trim();



        logger.info(districtCheckValue);
        logger.info(streetCheckValue);

        assertActions().assertEquals(districtCheckValue, district);
        assertActions().assertEquals(streetCheckValue, street);
        assertActions().assertEquals(maleFlatmatesValue, menFlatmate);
        assertActions().assertEquals(femaleFlatmatesValue, womenFlatmate);
        assertActions().assertEquals(prefMaleFlatmatesValue, yesOption);
        assertActions().assertEquals(prefFemaleFlatmatesValue, yesOption);
        assertActions().assertEquals(prefCoupleFlatmatesValue, yesOption);
        assertActions().assertEquals(petAllowedValue, yesOption);
        assertActions().assertEquals(smokingAllowedValue, yesOption);
        assertActions().assertEquals(priceValue, price);
        assertActions().assertEquals(minStayValue, minStay);
        assertActions().assertEquals(availabilityDateValue, availabilityDate);

        //button
        confirmButton.click();

        //successMsg
        boolean isSuccessMsgDisplayed = false;
        try {
            driverWait().until(ExpectedConditions.visibilityOf(successMsg));
            isSuccessMsgDisplayed = true;
         } catch(TimeoutException e) {
             logger.info("success msg not displayed");
         }
         
         assertActions().assertTrue(isSuccessMsgDisplayed, "Checking if success msg was displayed after adding room for rent");

        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }


    }

}