package tests;

import data.providers.AttractionsDataProvider;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AttractionsPage;

import static data.PagesUrl.Urls.ATTRACTIONS_PAGE_URL;

public class AttractionsSearchTest extends BaseTestRunner {
    AttractionsPage attractionsPage;

    @BeforeClass
    public void preConditions() {
        attractionsPage = new AttractionsPage(driver);
    }

    @Description("Compare prices of search result with selected price filters")
    @Test(dataProvider = "searchStrAndPriceFilter", dataProviderClass = AttractionsDataProvider.class)
    public void verifySearchResult(String cityName, Boolean[] activeCheckboxes) {
        openPage(ATTRACTIONS_PAGE_URL);
        attractionsPage.enterSearchQuery(cityName)
                .clickSearchBtn()
                .clickShowAllResults()
                .selectPriceFilter(activeCheckboxes);
        Assert.assertTrue(attractionsPage.compareSelectedPriceWithResults(),
                "Compare chosen and result prices failed");
    }
}
