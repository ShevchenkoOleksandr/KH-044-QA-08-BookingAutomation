package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AttractionsPriceFilter {
    protected WebDriver driver;
    protected List<WebElement> selectPriceBlocks;

    protected By priceBlock = By.xpath("//*[@name='filter_by_price']/parent::div");

    public AttractionsPriceFilter(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements(){
        selectPriceBlocks = driver.findElements(priceBlock);
    }

}
