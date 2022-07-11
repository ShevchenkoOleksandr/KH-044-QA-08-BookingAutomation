package pages;

import tools.StringToResults;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.greaterThan;

public class ResultsPage extends BasePage {
    protected By locationUp = By.cssSelector("div[data-testid='pick-up-location']");
    protected By locationOff = By.cssSelector("div[data-testid='drop-off-location']");
    protected By dateUpResult = By.cssSelector("div[data-testid='pick-up-date']");
    protected By dateOffResult = By.cssSelector("div[data-testid='drop-off-date']");
    protected By resultString = By.cssSelector("div[data-testid='page-title']");


    public ResultsPage(WebDriver driver) {
        super(driver);
    }


    public ResultsPage checkLocations(String locationUpOff, String locationResults) {
        waitPageRefresh();
        WebElement pickUpLocation = driver.findElement(locationUp);
        WebElement dropOffLocation = driver.findElement(locationOff);
        WebElement countCars = driver.findElement(resultString);
        assertThat(pickUpLocation.getText(), equalToIgnoringWhiteSpace(locationUpOff));
        assertThat(dropOffLocation.getText(), equalToIgnoringWhiteSpace(locationUpOff));
        assertThat(StringToResults.stringToLocation(countCars.getText()), equalToIgnoringWhiteSpace(locationResults));
        return this;
    }

    public ResultsPage checkDates(String dateUp, String dateOff) {
        waitPageRefresh();
        WebElement pickUpDate = driver.findElement(dateUpResult);
        WebElement dropOffDate = driver.findElement(dateOffResult);
        assertThat(StringToResults.stringToDate(pickUpDate.getText()), equalToIgnoringWhiteSpace(dateUp));
        assertThat(StringToResults.stringToDate(dropOffDate.getText()), equalToIgnoringWhiteSpace(dateOff));
        return this;
    }

    public ResultsPage checkCountCars() {
        waitPageRefresh();
        WebElement countCars = driver.findElement(resultString);
        assertThat(StringToResults.stringToResults(countCars.getText()), greaterThan(0));
        return this;
    }

}
