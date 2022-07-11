package tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;


import static data.PagesUrl.Urls.HOME_PAGE_URL;

public class ChangeLanguageTest extends BaseTestRunner{

    HomePage homePage;

    @Test
    public void languageSwitch() {
        openPage(HOME_PAGE_URL);
        homePage = new HomePage(driver);
        homePage = homePage.acceptCookies()
                .clickLanguageButton()
                .switchLanguage("de")
                .checkButtonsLanguage();
    }
}
