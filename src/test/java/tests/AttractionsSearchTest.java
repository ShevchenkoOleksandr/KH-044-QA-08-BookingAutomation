package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AttractionsPage;

import java.util.TreeMap;

import static data.PagesUrl.Urls.ATTRACTIONS_PAGE_URL;

public class AttractionsSearchTest extends BaseTestRunner{
    AttractionsPage attractionsPage;

    @BeforeClass
    public void preConditions() {
//        openPage(ATTRACTIONS_PAGE_URL);
        attractionsPage = new AttractionsPage(driver);
    }

    @Test
    public void verifySearchResult() throws InterruptedException {
        openPage(ATTRACTIONS_PAGE_URL);
        attractionsPage.enterSearchQuery("Paris");
        Thread.sleep(2000);
        attractionsPage.clickSearchBtn();
        Thread.sleep(2000);
        attractionsPage.clickShowAllResults();
        Thread.sleep(5000);
    }
}
