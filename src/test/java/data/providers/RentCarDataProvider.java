package data.providers;

import org.testng.annotations.DataProvider;

public class RentCarDataProvider {
    @DataProvider(name = "orderData")
    public static Object[][] orderData() {
        return new Object[][]{
                {"Kraków",
                        "Kraków, Lesser Poland, Poland",
                        "2022-10-10",
                        "2022-12-02"
                }// {location, fullCityName, checkInDate, checkOutDate}
        };
    }
    @DataProvider(name = "startData")
    public static Object[][] startData() {
        return new Object[][]{
                {"USD", "en-gb"
                }// {currency, language}
        };
    }
}
