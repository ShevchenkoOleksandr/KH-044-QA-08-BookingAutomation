package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FlightsPage extends BasePage {

    public FlightsPage(WebDriver driver) {
        super(driver);
    }

    private By radioBtnOneWay = By.cssSelector("[data-testid='searchbox_controller_trip_type_ONEWAY']");
    private By whereFromBox = By.cssSelector("[data-testid='searchbox_origin']");
    private By inputWhereFrom = By.cssSelector("[data-testid='searchbox_origin_input']");
    private By inputWhereTo = By.cssSelector("[data-testid='searchbox_destination'] input");
    private By waitActivationForWhereTo = By.cssSelector("[data-testid='searchbox_destination'] [aria-describedby]");
    private By choseDateBtn = By.cssSelector("[data-testid='searchbox_date_picker_desktop_calendar']");
    private By searchBtn = By.cssSelector("[class*='searchbox'] button.wide");
    private By whereFromSelected = By.xpath("//*[@data-testid='searchbox_origin_input']/preceding-sibling::*//span[@aria-hidden]");
    private By checkboxInProposeResult = By.xpath("//*[@data-testid='autocomplete_result']/following-sibling::*//input[@type='checkbox']");
    private By autoCompleteSheet = By.cssSelector("[class*='autoCompleteSheet']");
    private By searchResultCard = By.cssSelector("[id^='flightcard']");


    public FlightsPage choseOneWay() {
        driver.findElement(radioBtnOneWay).click();
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
        List<WebElement> checkboxes = driver.findElements(checkboxInProposeResult);
        if (checkboxes.size() > 0) checkboxes.forEach(checkbox -> checkbox.click());
//        clickFreeSpaceForHideWhereToMenu();
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


}
