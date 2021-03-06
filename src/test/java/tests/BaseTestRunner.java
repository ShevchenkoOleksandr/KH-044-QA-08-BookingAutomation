package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

import static data.Constants.TimeoutVariable.IMPLICIT_WAIT_SECONDS;
import static data.Constants.TimeoutVariable.CLEAR_COOKIES_AND_STORAGE;


public abstract class BaseTestRunner {
    protected WebDriver driver;

    public void openPage(String url){
        driver.get(url);
    }

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass(alwaysRun = true)
    public void setUpBeforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
        driver.manage().window().maximize();
    }
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
    @AfterTest
    public void clearCookiesAndLocalStorage (){
         if(CLEAR_COOKIES_AND_STORAGE){
         JavascriptExecutor javascriptExecutor =(JavascriptExecutor) driver;
         driver.manage().deleteAllCookies();
          javascriptExecutor.executeScript("window.sessionStorage.clear()");
    }

    }
}
