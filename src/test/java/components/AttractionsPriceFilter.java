package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import tools.PriceFilterHelper;
import java.time.Duration;
import java.util.List;
import static data.Constants.TimeoutVariable.EXPLICIT_WAIT_SECONDS;
import static data.Constants.TimeoutVariable.IMPLICIT_WAIT_SECONDS;

public class AttractionsPriceFilter {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected List<WebElement> selectPriceBlocks;
    protected PriceFilterHelper priceFilterHelper = new PriceFilterHelper();
    protected By priceBlock = By.xpath("//*[@name='filter_by_price']/parent::div");
    protected By resultCard = By.cssSelector("[data-testid='card']");

    public AttractionsPriceFilter(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));
        initElements();
    }

    private void initElements() {
        selectPriceBlocks = driver.findElements(priceBlock);
    }

    public void selectCheckboxes(Boolean[] activeCheckboxes) {
        for (int i = 0;
             i < selectPriceBlocks.size() && i < activeCheckboxes.length;
             i++) {
            WebElement currentPriceBox = selectPriceBlocks.get(i);
            WebElement resultCardBox = driver.findElement(resultCard);
            if (activeCheckboxes[i]) {
                currentPriceBox.click(); //click on this checkbox if it set in DataProvider
                priceFilterHelper.parsePriceDiapasonStr(currentPriceBox.getText());

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
                wait.until(ExpectedConditions.stalenessOf(resultCardBox));
                wait.until(ExpectedConditions.presenceOfElementLocated(resultCard));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
            }
        }
    }

    public PriceFilterHelper getPriceFilterHelper() {
        return priceFilterHelper;
    }
}
