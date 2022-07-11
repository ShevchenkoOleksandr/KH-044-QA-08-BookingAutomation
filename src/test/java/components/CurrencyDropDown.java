package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CurrencyDropDown {
    private WebDriver driver;
    private WebElement searchCurrency;

    public CurrencyDropDown(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getSearchCurrency(String newCurrency) {
        searchCurrency = driver.findElement(By.xpath("//div[@class='bui-modal__inner']" +
                "//div[@class='bui-traveller-header__currency'] [contains(text(), '"+newCurrency+"')]"));
        return searchCurrency;
    }


    public void setNewCurrency(String newCurrency) {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getSearchCurrency(newCurrency).click();
    }
}
