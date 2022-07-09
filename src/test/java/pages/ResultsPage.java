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
    private WebElement pickUpLocation;
    private WebElement pickUpDate;
    private WebElement dropOffLocation;
    private WebElement dropOffDate;
    private WebElement countCars;


    public ResultsPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        pickUpLocation = driver.findElement(By.cssSelector("div[data-testid='pick-up-location']"));
        pickUpDate = driver.findElement(By.cssSelector("div[data-testid='pick-up-date']"));
        dropOffLocation = driver.findElement(By.cssSelector("div[data-testid='drop-off-location']"));
        dropOffDate = driver.findElement(By.cssSelector("div[data-testid='drop-off-date']"));
        countCars = driver.findElement(By.cssSelector("div[data-testid='page-title']"));
    }


    public String getPickUpLocationText() {
        return pickUpLocation.getText();
    }

    public String getPickUpDateText() {
        return pickUpDate.getText();
    }

    public String getDropOffLocationText() {
        return dropOffLocation.getText();
    }

    public String getDropOffDateText() {
        return dropOffDate.getText();
    }

    public String getCountCarsText() {
        return countCars.getText();
    }

    public ResultsPage checkLocations(String locationUpOff, String locationResults) {
        assertThat(getPickUpLocationText(), equalToIgnoringWhiteSpace(locationUpOff));
        assertThat(getDropOffLocationText(), equalToIgnoringWhiteSpace(locationUpOff));
        assertThat(StringToResults.stringToLocation(getCountCarsText()), equalToIgnoringWhiteSpace(locationResults));
        return this;
    }

    public ResultsPage checkDates(String dateUp, String dateOff) {
        assertThat(StringToResults.stringToDate(getPickUpDateText()), equalToIgnoringWhiteSpace(dateUp));
        assertThat(StringToResults.stringToDate(getDropOffDateText()), equalToIgnoringWhiteSpace(dateOff));
        return this;
    }

    public ResultsPage checkCountCars() {
        assertThat(StringToResults.stringToResults(getCountCarsText()), greaterThan(0));
        return this;
    }


}
