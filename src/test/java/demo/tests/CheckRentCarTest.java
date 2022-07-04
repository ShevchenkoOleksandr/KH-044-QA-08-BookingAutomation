package demo.tests;
import demo.pages.RentCarPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class CheckRentCarTest extends BaseTestRunner{




    @Test (description = "rent a car")
    public void rentCar() {
        RentCarPage rentCarPage = new RentCarPage(driver);



        driver.findElement(rentCarButton).click();
        // select city
        driver.findElement(By.cssSelector("#ss_origin")).click();
        driver.findElement(By.cssSelector("#ss_origin")).clear();
        driver.findElement(By.cssSelector("#ss_origin")).sendKeys("Krakow");
        driver.findElement(listCities).click();
        // select check-in date
        driver.findElement(checkInButton).click();

        // select check-out date
        driver.findElement(checkOutButton).click();



        //click on searchButton
        driver.findElement(searchButton).click();

//expected result
        Assert.assertEquals("","https://cars.booking.com/SearchResults");
//        assertThat(contains);
    }
}
