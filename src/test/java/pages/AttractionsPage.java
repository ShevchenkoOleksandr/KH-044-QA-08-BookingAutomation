package pages;

import components.AttractionsPriceFilter;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import tools.PriceFilterHelper;

import java.time.Duration;
import java.util.List;

import static data.Constants.TimeoutVariable.IMPLICIT_WAIT_SECONDS;

public class AttractionsPage extends BasePage {

    public AttractionsPage(WebDriver driver) {
        super(driver);
    }

    protected PriceFilterHelper priceFilterHelper;
    protected AttractionsPriceFilter priceFilter;
    protected By textInput = By.cssSelector("form[action^='/attractions/searchresults'] input");
    protected By submitButton = By.cssSelector("form[action^='/attractions/searchresults'] button");
    protected By showAllBtn = By.cssSelector("a[href*='sort_by=attr_book_score']");
    protected By resultCardPrice = By.cssSelector("[data-testid='card'] [style*='text-align: right']");
    protected By searchBarResult = By.cssSelector("[data-testid='search-bar-result']");

    protected WebElement searchSubmitBtn;

    protected Actions action = new Actions(driver);

    @Step("Enter search query in field")
    public AttractionsPage enterSearchQuery(String query) {
        WebElement input = driver.findElement(textInput);
        input.click();
        searchSubmitBtn = driver.findElement(submitButton); //find button when it is only one
        input.sendKeys(query);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        wait.until(ExpectedConditions.presenceOfElementLocated(searchBarResult));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
        return this;
    }

    @Step("Click search button")
    public AttractionsPage clickSearchBtn() {
        action.moveToElement(searchSubmitBtn).click().build().perform();
        return this;
    }

    @Step("Click button 'Show all results'")
    public AttractionsPage clickShowAllResults() {
        List<WebElement> showAll = driver.findElements(showAllBtn);
        if (showAll.size() >= 1) {
            showAll.get(0).click();
        }
        return this;
    }

    @Step("Select price filters")
    public AttractionsPage selectPriceFilter(Boolean[] activeCheckboxes) {
        priceFilter = new AttractionsPriceFilter(driver);
        priceFilter.selectCheckboxes(activeCheckboxes);
        priceFilterHelper = priceFilter.getPriceFilterHelper();
        return this;
    }

    @Step("Compare selected price filters with prices of result")
    public boolean compareSelectedPriceWithResults() {
        List<WebElement> resultPrices = driver.findElements(resultCardPrice);

        for (WebElement cardPriceElement : resultPrices) {
            if (!cardPriceElement.isDisplayed()) {
                continue;
            }
            float cardPrice = priceFilterHelper.parsePriceFromResultCard(cardPriceElement.getText());
            if (!priceFilterHelper.compareWithPriceDiapason(cardPrice)) {
                Assert.fail("Current price: " + cardPrice + " not in diapason "
                        + priceFilterHelper.getDiapasonString());
                break;
            }
        }
        return true;
    }
}
