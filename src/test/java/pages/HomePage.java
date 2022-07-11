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
    private final By rentCarBtn = By.cssSelector("a[href *='/cars/index']");
    private final By flightsButton = By.xpath("//a[@data-decider-header='flights']");
    private final By staysButton = By.xpath("//a[@class='bui-tab__link bui-tab__link--selected']");
    private final By attractionsButton = By.xpath("//a[@data-decider-header='attractions']");
    private final By airportTaxiButton = By.cssSelector("a[href *='/taxi/index']");

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

    public WebElement getCurrencyButton() {
        return currencyButton;
    }

    public WebElement getCurrencyText() {
        return currencyText;
    }

    public WebElement getLanguageButton() {
        return languageButton;
    }

    public WebElement getRentCarButton() {
        return rentCarButton;
    }

    public CurrencyDropDown getCurrencyDropDown() {
        return currencyDropDown;
    }

    public LanguageDropDown getLanguageDropDown() {
        return languageDropDown;
    }

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
    @Step("Click on CurrencyButton")
    public HomePage clickCurrencyButton() {
        getCurrencyButton().click();
        setWait(2000);
        return this;
    }
    @Step("Click on LanguageButton")
    public HomePage clickLanguageButton() {
        getLanguageButton().click();
        setWait(2000);
        return this;
    }

    @Step("Goto RentCarPage")
    public RentCarPage gotoRentCarPage() {
        setWait(5000);
        getRentCarButton().click();
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

    public HomePage checkButtonsLanguage() {
        setWait(2000);

        String deStays = "Aufenthalte";
        String deCarRent = "Mietwagen";
        String deAttractions = "Attraktionen";
        String deAirportTaxi = "Flughafentaxis";

        WebElement staysBtn = driver.findElement(staysButton);
        Assert.assertEquals(staysBtn.getText(), deStays);

        WebElement carRentBtn = driver.findElement(rentCarBtn);
        Assert.assertEquals(carRentBtn.getText(), deCarRent);

        WebElement attractionsBtn = driver.findElement(attractionsButton);
        Assert.assertEquals(attractionsBtn.getText(), deAttractions);

        WebElement airportTaxiBtn = driver.findElement(airportTaxiButton);
        Assert.assertEquals(airportTaxiBtn.getText(), deAirportTaxi);

        return this;
    }
}
