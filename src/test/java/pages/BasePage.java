package pages;

import components.CurrencyDropDown;
import components.LanguageDropDown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static data.Constants.TimeoutVariable.EXPLICIT_WAIT_SECONDS;


public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private WebElement currencyButton;
    private WebElement currencyText;
    private WebElement languageButton;
    private WebElement rentCarButton;
    private CurrencyDropDown currencyDropDown;
    private LanguageDropDown languageDropDown;

    private final By acceptCookiesButton = By.cssSelector("button#onetrust-accept-btn-handler");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));
//        initElements();
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
    }
    //Functional

}
