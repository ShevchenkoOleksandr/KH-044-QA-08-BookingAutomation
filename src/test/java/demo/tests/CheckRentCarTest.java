package demo.tests;
import demo.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class CheckRentCarTest extends BaseTestRunner{
    HomePage homePage;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
       homePage = new HomePage(driver);
        homePage = homePage.acceptCookies()
                .clickCurrencyButton()
                .switchCurrency("USD")
                .checkCurrency("USD")
                .clickLanguageButton()
                .switchLanguage("en-gb");
    }

    @Test (description = "rent a car")
    public void rentCar() {
        homePage.gotoRentCarPage()
                .scrollToCityField()
                .setNewCity("Krakow")
                .selectCity("Kraków, Lesser Poland, Poland")
                .setCheckInDate("2022-10-10")
                .setCheckOutDate("2022-12-02")
                .clickSearchButton()
                .checkLocations("Kraków", "Kraków")
                .checkDates("2022-10-10", "2022-12-02")
                .checkCountCars();

    }
}
