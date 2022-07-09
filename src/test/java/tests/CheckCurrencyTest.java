package tests;

import io.qameta.allure.Description;
import pages.HomePage;
import org.testng.annotations.Test;


import static data.PagesUrl.Urls.HOME_PAGE_URL;

public class CheckCurrencyTest extends BaseTestRunner {

    @Description("Check possibility to change currency")
    @Test(description = "Changing of currency")
    public void changeCurrency() {
        openPage(HOME_PAGE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies()
                .clickCurrencyButton()
                .switchCurrency("USD")
                .checkCurrency("USD");
    }
}
