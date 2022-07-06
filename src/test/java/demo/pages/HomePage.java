package demo.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickCurrencyButton() {
        getCurrencyButton().click();
        return this;
    }
    public HomePage clickLanguageButton() {
        getLanguageButton().click();
        return this;
    }
    public RentCarPage gotoRentCarPage() {
        getRentCarButton().click();
        return new RentCarPage(driver);
    }

    //Business Logic
    public HomePage switchCurrency(String newCurrency) {
        getCurrencyDropDown().setNewCurrency(newCurrency);
        return new HomePage(driver);
    }

    public HomePage switchLanguage(String newLanguage) {
        getLanguageDropDown().setNewLanguage(newLanguage);
        return new HomePage(driver);
    }

    public HomePage checkCurrency(String dueCurrency) {
        Assert.assertEquals(getCurrencyText().getText(), dueCurrency);
        return this;
    }

    public HomePage acceptCookies() {
        if (isAcceptCookiesButton()){
            clickCookieButton();
        }
        return this;
    }
}
