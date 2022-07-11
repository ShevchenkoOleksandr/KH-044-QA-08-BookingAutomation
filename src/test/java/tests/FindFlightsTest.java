package tests;

import data.providers.FlightsDataProvider;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FlightsPage;

import java.util.List;

import static data.PagesUrl.Urls.HOME_PAGE_URL;


public class FindFlightsTest extends BaseTestRunner {

    FlightsPage flightsPage;

    @BeforeClass
    public void preConditions() {
        openPage(HOME_PAGE_URL);
        flightsPage = new FlightsPage(driver);
    }

    @Test(dataProvider = "citiesData", dataProviderClass = FlightsDataProvider.class)
    public void verifyOneWaySearch(String whereFrom, String whereTo) {
        flightsPage
                .cookiesAccept()
                .flightBtn()
                .choseOneWay()
                .activateWhereFromInput()
                .clearWhereFromSelected()
                .enterWhereFrom(whereFrom)
                .selectAllFromProposeList()
                .activateWhereToInput()
                .enterWhereTo(whereTo)
                .selectAllFromProposeList()
                .clickFreeSpaceForHideWhereToMenu()
                .verifyOneWaySearch();
    }
}
