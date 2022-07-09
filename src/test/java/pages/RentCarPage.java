package pages;

import components.CalendarCheckInOut;
import components.ListCities;
import io.qameta.allure.Step;
import tools.StringToResults;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RentCarPage extends BasePage {
    private WebElement cityField;
    private WebElement checkInButton;
    private WebElement checkOutButton;
    private WebElement searchButton;
    private ListCities listCities;
    private CalendarCheckInOut calendarInOut;


    public RentCarPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        cityField = driver.findElement(By.cssSelector("#ss_origin"));
        checkInButton = driver.findElement(By.cssSelector("div[data-mode='checkin'] button"));
        checkOutButton = driver.findElement(By.cssSelector("div[data-mode='checkout'] button"));
        searchButton = driver.findElement(By.cssSelector("div.xpi__searchbox.rentalcars button[type='submit']"));
        listCities = new ListCities(driver);
        calendarInOut = new CalendarCheckInOut(driver);
    }

    public WebElement getCityField() {
        return cityField;
    }

    @Step("Click on city field")
    public RentCarPage clickCityField() {
        getCityField().click();
        getCityField().clear();
        return this;
    }

   @Step("Enter city name")
    public RentCarPage setNewCity(String city) {
        getCityField().sendKeys(city);
        setWait(2000);
        return this;
    }

    @Step("Select city from the list")
    public RentCarPage selectCity(String city) {
        listCities.setMyCity(city);
        return this;
    }

    public CalendarCheckInOut openCheckIn(){
        checkInButton.click();
        setWait(2000);
        return calendarInOut;
    }

    public CalendarCheckInOut openCheckOut(){
        checkOutButton.click();
        setWait(2000);
        return calendarInOut;
    }
    @Step("Select CheckInDate")
    public RentCarPage setCheckInDate(String checkInDate) {
        openCheckIn()
                .initElementsIn()
                .searchMonth(StringToResults.monthToMills(checkInDate))
                .setDay(StringToResults.daysToMills(checkInDate));
        openCheckIn();
        setWait(2000);
        return this;
    }

   @Step("Select CheckOutDate")
    public RentCarPage setCheckOutDate(String checkOutDate) {
        openCheckOut()
                .initElementsOut()
                .searchMonth(StringToResults.monthToMills(checkOutDate))
                .setDay(StringToResults.daysToMills(checkOutDate));
        setWait(2000);
        return this;
    }

    public RentCarPage scrollToCityField() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", cityField);
        setWait(2000);
        return this;
    }

   @Step("Click on SearchButton")
    public ResultsPage clickSearchButton (){
        searchButton.click();
        return new ResultsPage(driver);
    }

}
