package tests;
import data.providers.RentCarDataProvider;
import io.qameta.allure.Description;
import pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static data.PagesUrl.Urls.HOME_PAGE_URL;


public class CheckRentCarTest extends BaseTestRunner{
    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        openPage(HOME_PAGE_URL);
        homePage = new HomePage(driver);
        homePage = homePage.acceptCookies()
            .clickCurrencyButton()
            .switchCurrency("USD")
            .checkCurrency("USD")
            .clickLanguageButton()
            .switchLanguage("en-gb");
}

    @Description("Check possibility to search cars for rent by city name")
    @Test(description = "rent a car",
            dataProvider = "orderData",
            dataProviderClass = RentCarDataProvider.class)
    public void rentCar(String location,
                        String fullCityName,
                        String checkInDate,
                        String checkOutDate) {
        homePage.gotoRentCarPage()
                .scrollToCityField()
                .clickCityField()
                .setNewCity(location)
                .selectCity(fullCityName)
                .setCheckInDate(checkInDate)
                .setCheckOutDate(checkOutDate)
                .clickSearchButton()
                .checkLocations(location, location)
                .checkDates(checkInDate, checkOutDate)
                .checkCountCars();
    }
}
