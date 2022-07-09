package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class AttractionsPage extends BasePage {

    public AttractionsPage(WebDriver driver) {
        super(driver);
    }

    protected By searchForm = By.cssSelector("form[action^='/attractions/searchresults']");
    protected By textInput = By.cssSelector("form[action^='/attractions/searchresults'] input");
    protected By submitButton = By.cssSelector("form[action^='/attractions/searchresults'] button");
    protected By showAllBtn = By.cssSelector("a[href*='sort_by=attr_book_score']");

    protected WebElement searchSubmitBtn;

    protected Actions action = new Actions(driver);

    public AttractionsPage enterSearchQuery(String query) {
        WebElement input = driver.findElement(textInput);
        input.click();
        searchSubmitBtn = driver.findElement(submitButton); //find button when it is only one
        input.sendKeys(query);
        return this;
    }

    public AttractionsPage clickSearchBtn() {
//        searchSubmitBtn.click();
        action.moveToElement(searchSubmitBtn).click().build().perform();
        return this;
    }

    public AttractionsPage clickShowAllResults(){
        List<WebElement> showAll = driver.findElements(showAllBtn);
        if(showAll.size()>=1){
            showAll.get(0).click();
        }
        return this;
    }

    public AttractionsPage selectPriceFilter() {

        return this;
    }
}
