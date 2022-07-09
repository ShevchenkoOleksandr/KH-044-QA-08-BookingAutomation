package data.providers;

import org.testng.annotations.DataProvider;

public class AttractionsDataProvider {

    @DataProvider(name = "searchStrAndPriceFilter")
    public static Object[][] searchStrAndPriceFilter() {
        String city = "Paris";
        return new Object[][]{
                {city, new Boolean[]{true, false, false, false, false}},
                {city, new Boolean[]{false, true, false, false, false}},
                {city, new Boolean[]{false, false, true, false, false}},
                {city, new Boolean[]{false, false, false, true, false}},
                {city, new Boolean[]{false, false, false, false, true}},
                {city, new Boolean[]{true, true, false, false, false}},
                {city, new Boolean[]{false, true, true, true, false}},
                {city, new Boolean[]{false, false, false, true, true}}
        };
    }
}
