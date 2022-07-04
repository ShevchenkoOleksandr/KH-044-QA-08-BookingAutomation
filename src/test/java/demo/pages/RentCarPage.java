package demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RentCarPage extends BasePage {
    private WebElement cityField;
    private WebElement listCities;
    private WebElement checkInButton;
    private WebElement checkOutButton;
    private WebElement searchButton;


    public RentCarPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        cityField = driver.findElement(By.cssSelector("#ss_origin"));
        listCities = driver.findElement(By.xpath("//input[@id='ss_origin']/parent::*/following-sibling::ul[1]/li[1]"));
        checkInButton = driver.findElement(By.cssSelector("div[data-mode='checkin'] button"));
        checkOutButton = driver.findElement(By.cssSelector("div[data-mode='checkout'] button"));
        searchButton = driver.findElement(By.cssSelector("div.xpi__searchbox.rentalcars button[type='submit']"));
    }

//cityField
    public WebElement getCityField() {
        return cityField;
    }
    public void clickCityField () {
        getCityField().click();
    }

    public WebElement clearCityField (){
        getCityField().clear();
        return getCityField();
    }

    public void setCityField(String city){

        getCityField().sendKeys(city);
    }

    //Business Logic
    public RentCarPage switchCurrency(String newCurrency) {
        getCurrencyDropDown().setNewCurrency(newCurrency);
        return  new RentCarPage(driver);
    }
    public RentCarPage switchLanguage(String newLanguage) {
        getLanguageDropDown().setNewLanguage(newLanguage);
        return  new RentCarPage(driver);
    }
}
