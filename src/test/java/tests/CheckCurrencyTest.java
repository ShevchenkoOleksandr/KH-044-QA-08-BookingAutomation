package tests;

import pages.HomePage;
import org.testng.annotations.Test;


import static data.PagesUrl.Urls.HOME_PAGE_URL;

public class CheckCurrencyTest extends BaseTestRunner {


    @Test(description = "Changing of currency")
    public void changeCurrency() {
        openPage(HOME_PAGE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.clickCurrencyButton()
                .switchCurrency("USD")
                .checkCurrency("USD")
                .clickLanguageButton()
                .switchLanguage("en-gb");

//       homePage.clickLanguageButton();
//        homePage.switchLanguage("uk");

    }
}
