package pages;

import components.CurrencyDropDown;
import components.LanguageDropDown;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomePage extends BasePage {
    private WebElement currencyButton;
    private WebElement currencyText;
    private WebElement languageButton;
    private WebElement rentCarButton;
    private CurrencyDropDown currencyDropDown;
    private LanguageDropDown languageDropDown;

    private final By acceptCookiesButton = By.cssSelector("button#onetrust-accept-btn-handler");

    public HomePage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
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

    // currencyText
    public WebElement getCurrencyText() {
        return currencyText;
    }

    // languageButton
    public WebElement getLanguageButton() {
        return languageButton;
    }

    // rentCarButton
    public WebElement getRentCarButton() {
        return rentCarButton;
    }

    //currencyDropDown
    public CurrencyDropDown getCurrencyDropDown() {
        return currencyDropDown;
    }

    //languageDropDown
    public LanguageDropDown getLanguageDropDown() {
        return languageDropDown;
    }

    //cookieButton
    public boolean isAcceptCookiesButton() {
        try {
            return driver.findElement(acceptCookiesButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCookieButton() {
        driver.findElement(acceptCookiesButton).click();
        setWait(2000);
    }

    public HomePage clickCurrencyButton() {
        getCurrencyButton().click();
        setWait(2000);
        return this;
    }

    public HomePage clickLanguageButton() {
        getLanguageButton().click();
        setWait(2000);
        return this;
    }

    public RentCarPage gotoRentCarPage() {
        getRentCarButton().click();
        setWait(2000);
        return new RentCarPage(driver);
    }

    //Business Logic
    @Step("Switch Currency")
    public HomePage switchCurrency(String newCurrency) {
        getCurrencyDropDown().setNewCurrency(newCurrency);
        return new HomePage(driver);
    }

    @Step("Switch Language")
    public HomePage switchLanguage(String newLanguage) {
        getLanguageDropDown().setNewLanguage(newLanguage);
        return new HomePage(driver);
    }
    @Step("Check Actual Currency")
    public HomePage checkCurrency(String dueCurrency) {
        Assert.assertEquals(getCurrencyText().getText(), dueCurrency);
        return this;
    }
    @Step("Accept Cookies if needed")
    public HomePage acceptCookies() {
        if (isAcceptCookiesButton()) {
            clickCookieButton();
        }
        return this;
    }

}
