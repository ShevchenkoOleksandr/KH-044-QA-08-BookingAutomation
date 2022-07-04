package demo.pages;

import demo.components.CurrencyDropDown;
import demo.components.LanguageDropDown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class BasePage {
    protected WebDriver driver;

    private WebElement currencyButton;
    private WebElement currencyText;
    private WebElement languageButton;
    private WebElement rentCarButton;
    private CurrencyDropDown currencyDropDown;
    private LanguageDropDown languageDropDown;

    public BasePage (WebDriver driver) {
        this.driver=driver;
        initElements ();

    }

    private void initElements () {
        currencyButton = driver.findElement(By.cssSelector("[data-modal-header-async-type=currencyDesktop]"));
        currencyText = driver.findElement(By.cssSelector("[data-modal-header-async-type=currencyDesktop]" +
                " span.bui-button__text span:first-child"));
        languageButton = driver.findElement(By.cssSelector("[data-modal-id='language-selection']"));
        rentCarButton = driver.findElement(By.cssSelector("a[href *='/cars/index']"));
        currencyDropDown = new CurrencyDropDown(driver);
        languageDropDown = new LanguageDropDown(driver);
    }

    // currencyButton
    public WebElement getCurrencyButton() {
        return currencyButton;
    }

    public void clickCurrencyButton (){
        getCurrencyButton().click();
    }

    // currencyText
    public WebElement getCurrencyText() {
        return currencyText;
    }
    // languageButton
    public WebElement getLanguageButton() {
        return languageButton;
    }

    public void clickLanguageButton (){
        getLanguageButton().click();
    }
    // rentCarButton
    public WebElement getRentCarButton() {
        return rentCarButton;
    }

    public void clickRentCarButton (){
        getRentCarButton().click();
    }

    //currencyDropDown
    public CurrencyDropDown getCurrencyDropDown (){
        return currencyDropDown;
    }
    //languageDropDown
    public LanguageDropDown getLanguageDropDown (){
        return languageDropDown;
    }

    //Functional

    public void chooseCurrency(String newCurrency) {
        getCurrencyDropDown().setNewCurrency(newCurrency);
    }

    public void checkCurrency (String dueCurrency) {
        Assert.assertEquals(getCurrencyText().getText(), dueCurrency);
    }

    public void chooseLanguage(String newLanguage) {
        getLanguageDropDown().setNewLanguage(newLanguage);
    }
}
