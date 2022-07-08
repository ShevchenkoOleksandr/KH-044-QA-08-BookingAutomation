package tests;

import data.providers.FlightsDataProvider;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FlightsPage;

import java.util.List;

import static data.PagesUrl.Urls.FLIGHTS_PAGE_URL;


public class FindFlightsTest extends BaseTestRunner{

    FlightsPage flightsPage;

    @BeforeClass
    public void preConditions() {
        openPage(FLIGHTS_PAGE_URL);
        flightsPage = new FlightsPage(driver);
    }

    @BeforeMethod
    public void fillFieldsOneWaySearch() throws InterruptedException {
        flightsPage.choseOneWay()
                .activateWhereFromInput()
                .clearWhereFromSelected()
                .enterWhereFrom("Barcelona")
                .selectAllFromProposeList()
                .activateWhereToInput()
                .enterWhereTo("Milan")
                .selectAllFromProposeList()
                .clickFreeSpaceForHideWhereToMenu();
    }

//    @Test(dataProvider = "citiesData", dataProviderClass = FlightsDataProvider.class)
    public void verifyOneWaySearch(String from, String to) {
        System.out.println(to + " " + from);
        flightsPage.clickSearchBtn();
        List<WebElement> searchResultCards = flightsPage.getSearchResults();
        Assert.assertFalse(searchResultCards.isEmpty(), "Search result flight cards not found");
    }

}
