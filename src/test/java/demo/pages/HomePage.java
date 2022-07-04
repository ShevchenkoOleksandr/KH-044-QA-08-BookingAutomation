package demo.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements(){

    }

    public RentCarPage rentCarPage() {
        return new RentCarPage(driver);
    }
    //Business Logic
    public HomePage switchCurrency(String newCurrency) {
        getCurrencyDropDown().setNewCurrency(newCurrency);
        return  new HomePage(driver);
    }
    public HomePage switchLanguage(String newLanguage) {
        getLanguageDropDown().setNewLanguage(newLanguage);
        return  new HomePage(driver);
    }
}
