package pages;

import components.CurrencyDropDown;
import components.LanguageDropDown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static data.Constants.TimeoutVariable.EXPLICIT_WAIT_SECONDS;


public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));
<<<<<<< HEAD
//        initElements();
=======
>>>>>>> 253ceb3260592d99082ad051bab6c0cc96fc91e8
    }

    protected void setWait(long millsWait) {
        try {
            Thread.sleep(millsWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected void waitPageRefresh () {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(webDriver -> ((JavascriptExecutor)webDriver))
                .executeScript("return document.readyState")
                .equals("complete");
    }
}
