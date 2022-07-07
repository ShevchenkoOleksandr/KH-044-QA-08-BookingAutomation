package tests;

import pages.HomePage;

import org.testng.annotations.Test;

public class CheckCurrencyTest extends BaseTestRunner {


    @Test(description = "Changing of currency")
    public void changeCurrency() {
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
