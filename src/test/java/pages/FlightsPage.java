package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class FlightsPage extends BasePage {

    public FlightsPage(WebDriver driver) {
        super(driver);
    }

    private final By acceptCookiesButton = By.cssSelector("button#onetrust-accept-btn-handler");
    private final By flightsButton = By.xpath("//a[@data-decider-header='flights']");
    private final By radioBtnOneWay = By.cssSelector("[data-testid='searchbox_controller_trip_type_ONEWAY']");
    private final By whereFromBox = By.cssSelector("[data-testid='searchbox_origin']");
    private final By inputWhereFrom = By.cssSelector("[data-testid='searchbox_origin_input']");
    private final By inputWhereTo = By.cssSelector("[data-testid='searchbox_destination'] input");
    private final By waitActivationForWhereTo = By.cssSelector("[data-testid='searchbox_destination'] [aria-describedby]");
    private final By choseDateBtn = By.cssSelector("[data-testid='searchbox_date_picker_desktop_calendar']");
    private final By searchBtn = By.cssSelector("[class*='searchbox'] button.wide");
    private final By whereFromSelected = By.xpath("//*[@data-testid='searchbox_origin_input']/preceding-sibling::*//span[@aria-hidden]");
    private final By checkboxInProposeResult = By.xpath("//*[@data-testid='autocomplete_result']/following-sibling::*//input[@type='checkbox']");
    private final By autoCompleteSheet = By.cssSelector("[class*='autoCompleteSheet']");
    private final By searchResultCard = By.cssSelector("[id^='flightcard']");


    public boolean isAcceptCookiesButton() {
        try {
            return driver.findElement(acceptCookiesButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public FlightsPage acceptCookies() {
        if (isAcceptCookiesButton()) {
            clickCookieButton();
        }
        return this;
    }
    public void clickCookieButton() {
        driver.findElement(acceptCookiesButton).click();
        setWait(2000);
    }

    public FlightsPage flightBtn() {
        setWait(5000);
        driver.findElement(flightsButton).click();

        return this;
    }

    public FlightsPage choseOneWay() {
        setWait(4000);
        WebElement radioBtn = driver.findElement(radioBtnOneWay);
        wait.until(ExpectedConditions.visibilityOf(radioBtn)).click();

        return this;
    }

    public FlightsPage activateWhereFromInput() {
        driver.findElement(whereFromBox).click();

        return this;
    }

    public FlightsPage enterWhereFrom(String cityFrom) {
        WebElement inputFrom = driver.findElement(inputWhereFrom);
        inputFrom.click();
        inputFrom.clear();
        inputFrom.sendKeys(cityFrom);

        return this;
    }

    public FlightsPage activateWhereToInput() {
        driver.findElement(inputWhereTo).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(waitActivationForWhereTo));

        return this;
    }

    public FlightsPage enterWhereTo(String cityTo) {
        WebElement inputTo = driver.findElement(inputWhereTo);
        Actions action = new Actions(driver);
        action.moveToElement(inputTo)
                .click()
                .sendKeys(cityTo)
                .build()
                .perform();

        return this;
    }

    public FlightsPage clearWhereFromSelected() {
        List<WebElement> closeBtns = driver.findElements(whereFromSelected);
        if (closeBtns.size() > 0) closeBtns.forEach(btn -> btn.click());

        return this;
    }

    public FlightsPage selectAllFromProposeList() {
        setWait(2500);
        List<WebElement> checkboxes = driver.findElements(checkboxInProposeResult);
        if (checkboxes.size() > 0) checkboxes.forEach(checkbox -> checkbox.click());

        return this;
    }


    public FlightsPage clickSearchBtn() {
        driver.findElement(searchBtn).click();

        return this;
    }

    public FlightsPage clickFreeSpaceForHideWhereToMenu() {
        WebElement searchBtnElement = driver.findElement(searchBtn);
        int btnHeight = Integer.parseInt(searchBtnElement.getDomProperty("offsetHeight"));
        int yOffsetForClickOnFreeSpace = (int) btnHeight / 2 + 10; // half of height + 10px
        Actions action = new Actions(driver);
        action.moveToElement(searchBtnElement, 0, yOffsetForClickOnFreeSpace)
                .click().build().perform();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(autoCompleteSheet)));

        return this;
    }

    public List<WebElement> getSearchResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchResultCard));
        List<WebElement> searchResults = driver.findElements(searchResultCard);

        return searchResults;
    }
    public FlightsPage verifyOneWaySearch() {
        clickSearchBtn();
        List<WebElement> searchResultCards = getSearchResults();
        Assert.assertFalse(searchResultCards.isEmpty(), "Search result flight cards not found");

        return this;
    }

}
