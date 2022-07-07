package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LanguageDropDown {
    private WebDriver driver;
    private WebElement searchLanguage;

    public LanguageDropDown(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    public void initElements() {

    }

    //setNewLanguage
    private WebElement getSearchLanguage(String newLanguage) {
        searchLanguage = driver.findElement(By.cssSelector("a[hreflang *='"+newLanguage+"']"));
        return searchLanguage;
    }


    public void setNewLanguage(String newLanguage) {
        getSearchLanguage(newLanguage).click();
    }
}
