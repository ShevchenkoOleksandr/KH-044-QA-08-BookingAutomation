package components;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListCities {
    private WebDriver driver;
    private WebElement myCity;

    public ListCities(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getCityFromList(String city) {
        try {
            myCity = driver.findElement(By.xpath("//input[@id='ss_origin']/parent::*/following-sibling::ul[1]/li[@data-value='"+city+"']"));
        } catch (NoSuchElementException e) {
            myCity = driver.findElement(By.xpath("//input[@id='ss_origin']/parent::*/following-sibling::ul[1]/li[3]"));
        }
        return myCity;
    }
    public void setMyCity(String city){
        getCityFromList(city).click();
    }
}
