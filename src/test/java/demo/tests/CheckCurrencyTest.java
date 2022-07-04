package demo.tests;

import demo.pages.HomePage;

import org.testng.annotations.Test;

public class CheckCurrencyTest extends BaseTestRunner {


    @Test(description = "Changing of currency")
    public void changeCurrency() {
        HomePage homePage = new HomePage(driver);
        homePage.clickCurrencyButton();
        homePage.switchCurrency("USD")
                .checkCurrency("USD");

       homePage.clickLanguageButton();
//        homePage.switchLanguage("uk");


//        Assert.assertEquals(driver.findElement(currencyText).getText(), newCurrency);
    }
}
