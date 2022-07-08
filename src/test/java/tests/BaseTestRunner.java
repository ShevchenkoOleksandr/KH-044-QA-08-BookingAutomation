package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

import static data.Constants.TimeoutVariable.IMPLICIT_WAIT_SECONDS;
import static data.PagesUrl.Urls.HOME_PAGE_URL;

public abstract class BaseTestRunner {
    protected WebDriver driver;

//    protected String pageUrl = HOME_PAGE_URL;
//    public void setPageUrl(String url) {
//        pageUrl = url;
//    }

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

}
