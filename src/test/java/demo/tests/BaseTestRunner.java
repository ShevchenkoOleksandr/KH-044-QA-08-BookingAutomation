package demo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTestRunner {
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass(alwaysRun = true)
    public void setUpBeforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://www.booking.com/");
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
//        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver.get("https://www.booking.com/");
    }

}
