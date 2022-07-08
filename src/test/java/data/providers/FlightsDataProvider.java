package data.providers;

import org.testng.annotations.DataProvider;


public class FlightsDataProvider {

    @DataProvider(name = "citiesData")
    public static Object[][] citiesData() {
        return new Object[][]{
                {"Barcelona", "Milan"}// {cityFrom, cityTo}
        };
    }
}
