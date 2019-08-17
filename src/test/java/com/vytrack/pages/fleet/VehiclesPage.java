package com.vytrack.pages.fleet;

import com.github.javafaker.Faker;
import com.vytrack.utilities.BasePage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vytrack.utilities.BrowserUtils.waitPlease;
import static com.vytrack.utilities.Driver.getDriver;

public class VehiclesPage extends BasePage {
    Faker faker=new Faker();

    @FindBy(xpath = "//label[text()='Page:']/following-sibling::ul//input")
    public WebElement pageNumber;

    @FindBy(css = "[title='Create Car']")
    public WebElement createACarBtn;

    @FindBy(css = "[id^='custom_entity_type_LicensePlate']")
    public WebElement licensePlate;

    @FindBy(css = "[id^='custom_entity_type_Driver']")
    public WebElement driverInput;

    @FindBy(css = "[id^='custom_entity_type_Location']")
    public WebElement location;

    @FindBy(css = "[id^='custom_entity_type_ModelYear']")
    public WebElement modelYear;

    @FindBy(css = "[id^='custom_entity_type_Color']")
    public WebElement color;

    @FindBy(css = "[id^='custom_entity_type_Power']")
    public WebElement power;

    @FindBy(css = "[class='btn btn-success action-button']")
    public WebElement saveAndClose;

    @FindBy(xpath = "//span[text()='General Information']")
    public WebElement generalInfo;

    @FindBy(xpath="//div[2]/div/table/tbody/tr[1]/td[1]")
    public WebElement anyCar;

    @FindBy(xpath = "//div/div[1]/div[2]/div/div/div/h5/span")
    public WebElement carGeneralInfo;

    @FindBy(xpath = "//div/table/thead/tr/th[1]/a/span[1]")
    public WebElement licensePlateBtnLocator;

    @FindBy(xpath = "//div/div[3]/div[1]/div/a[3]")
    public WebElement resetBtnLocator;

    @FindBy(xpath = "//div/div/div[1]/div[1]/div/a[@class='btn icons-holder-text no-hash']")
    public WebElement addEventBtnLocator;

    @FindBy(xpath = "//*[contains(@id,'oro_calendar_event_form_title')]")
    public WebElement eventFormTitleLocator;

    @FindBy(xpath = "//*[contains(@name,'organizerDisplayName')]")
    public WebElement organizerDisplayNameLocator;

    @FindBy(xpath = "//*[contains(@name,'organizerEmail')]")
    public WebElement organizerEmailLocator;

    @FindBy(xpath = "//*[contains(@id,'date_selector_oro_calendar_event_form_start')]")
    public WebElement eventFormStartDateLocator;

    public String getDate(){
        List<WebElement> dates= getDriver("chrome").findElements(By.className("day"));
        return "";
    }

    @FindBy(xpath = "//*[contains(@id,'date_selector_oro_calendar_event_form_end')]")
    public WebElement eventFormEndDateLocator;

    @FindBy(xpath = "//*[contains(@id,'time_selector_oro_calendar_event_form_start')]")
    public WebElement eventStartTimeLocator;

    @FindBy(xpath = "//*[contains(@id,'time_selector_oro_calendar_event_form_end')]")
    public WebElement eventEndTimeLocator;

    @FindBy(xpath = "//*[contains(@class,'ui-button ui-corner')]")
    public WebElement closeBtnForEventWindowLocator;

    @FindBy(xpath = "//div[11]/div/div/div/span[2]/button")
    public WebElement eventSaveBtnLocator;

    public void getDay(String day){
        String browser= ConfigurationReader.getProperty("browser");
        List<WebElement> days= Driver.getDriver(browser).findElements(By.xpath("//table/tbody/tr/td"));
        for (WebElement eachDay:days){
            if(eachDay.getText().equalsIgnoreCase(day)){
                eachDay.click();
            }
        }

    }
    public void getHour(String hour){
        String browser= ConfigurationReader.getProperty("browser");
        List<WebElement> hours=Driver.getDriver(browser).findElements(By.xpath("//body/div[9]/ul/li"));
        for(WebElement eachHour:hours){
            if(eachHour.getText().equalsIgnoreCase(hour)){
                eachHour.click();
                waitPlease(2);
            }
        }
    }
    public void addNewEvent(){
        waitPlease(2);
        addEventBtnLocator.click();
        waitPlease(5);
        eventFormTitleLocator.sendKeys(faker.name().title());
        waitPlease(1);
        organizerDisplayNameLocator.sendKeys(faker.name().fullName());
        waitPlease(1);
        organizerEmailLocator.sendKeys(faker.internet().emailAddress());
        waitPlease(1);
        eventFormStartDateLocator.click();
        waitPlease(1);
        getDay("22");
        waitPlease(2);
        eventStartTimeLocator.click();
        waitPlease(1);
        getHour("2:00 PM");
        waitPlease(2);
        eventSaveBtnLocator.click();
        waitPlease(4);
    }
    public Integer getPageNumber() {
        return Integer.valueOf(pageNumber.getAttribute("value"));
    }

    public void clickToCreateACar() {
        BrowserUtils.waitForStaleElement(createACarBtn);
        createACarBtn.click();
    }

    public void enterLicensePlate(String value) {
        licensePlate.clear();
        licensePlate.sendKeys(value);
    }

    public void enterDriver(String value) {
        //if we will do negative testing
        //and before there will be invalid text
        //we want to clear first
        //some applications have placeholders that might prevent correct text input
        driverInput.clear();
        driverInput.sendKeys(value);
    }

    public void enterLocation(String value) {
        location.clear();
        location.sendKeys(value);
    }

    public void enterModelYear(String value) {
        modelYear.clear();
        modelYear.sendKeys(value);
    }

    public void enterColor(String value) {
        color.clear();
        color.sendKeys(value);
    }

    public void enterPower(String value) {
        power.clear();
        power.sendKeys(value);
    }

    public void clickSaveAndClose() {
        saveAndClose.click();
    }

    public boolean verifyGeneralInformationIsDisplayed() {
        try {
            return generalInfo.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
