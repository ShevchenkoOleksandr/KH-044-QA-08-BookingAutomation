package demo.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CalendarCheckInOut {
    private WebDriver driver;
    private WebElement boxCalendar;
    private WebElement upButton;
    private WebElement month;


    public CalendarCheckInOut(WebDriver driver) {
        this.driver = driver;
    }

    public CalendarCheckInOut initElementsIn() {
        boxCalendar = driver.findElement(By.cssSelector("div.xp__dates-inner.xp__dates__checkin div.c2-calendar-viewport"));
        upButton = driver.findElement(By.cssSelector("div.xp__dates-inner.xp__dates__checkin " +
                "div.c2-button.c2-button-further span"));
        return this;
    }

    public CalendarCheckInOut initElementsOut() {
        boxCalendar = driver.findElement(By.cssSelector("div.xp__dates-inner.xp__dates__checkout div.c2-calendar-viewport"));
        upButton = driver.findElement(By.cssSelector("div.xp__dates-inner.xp__dates__checkout " +
                "div.c2-button.c2-button-further span"));
        return this;
    }
    public CalendarCheckInOut searchMonth(String idMonth) {
        while (!boxCalendar.findElement(By.cssSelector("div[data-id='" + idMonth + "']")).isDisplayed()) {
            upButton.click();
        }
        month = boxCalendar.findElement(By.cssSelector("div[data-id='" + idMonth + "']"));
        return this;
    }

    public void setDay(String idDays) {
        month.findElement(By.cssSelector("table tbody tr td[data-id='"+idDays+"']")).click();
    }
}
