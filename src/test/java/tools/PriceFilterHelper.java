package tools;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class PriceFilterHelper {
    private Integer minDiapasonPrice = null;
    private Integer maxDiapasonPrice = null;

    public String getDiapasonString() {
        return minDiapasonPrice + " - " + maxDiapasonPrice;
    }

    public void printMinMax() {
        System.out.println("minDiapasonPrice: " + minDiapasonPrice);
        System.out.println("maxDiapasonPrice: " + maxDiapasonPrice);
    }

    public void setMinDiapasonPrice(int minDiapasonPrice) {
        if ((this.minDiapasonPrice == null) || (minDiapasonPrice < this.minDiapasonPrice)) {
            this.minDiapasonPrice = minDiapasonPrice;
        }
    }

    public void setMaxDiapasonPrice(int maxDiapasonPrice) {
        if ((this.maxDiapasonPrice == null) || (maxDiapasonPrice > this.maxDiapasonPrice)) {
            this.maxDiapasonPrice = maxDiapasonPrice;
        }
    }

    public void parsePriceDiapasonStr(String diapasonStr) {
        diapasonStr = diapasonStr.replaceAll("\\(\\d+\\)", "");
        diapasonStr = diapasonStr.replaceAll("[^\\d\\-]", "");
        String[] minMax = diapasonStr.split("\\-");
        if (minMax.length == 1) { // for last option "price +"
            setMinDiapasonPrice(Integer.parseInt(minMax[0]));
            setMaxDiapasonPrice(Integer.MAX_VALUE);
        } else if (minMax.length == 2) { // for options witch has  min & max price diapason
            setMinDiapasonPrice(Integer.parseInt(minMax[0]));
            setMaxDiapasonPrice(Integer.parseInt(minMax[1]));
        }
    }

    public static float parsePriceFromResultCard(String priceStr) {
        priceStr = priceStr.replace(",", ".");
        priceStr = priceStr.replaceAll("[^\\d\\.]", "");
        float price = Float.parseFloat(priceStr);
        return price;
    }

    public boolean compareWithPriceDiapason(float price) {
        if (price >= minDiapasonPrice && price <= maxDiapasonPrice) {
            return true;
        } else return false;
    }

    public boolean compareWithPriceDiapason(int price) {
        return compareWithPriceDiapason((float) price);
    }

    public static void verifyMinToMaxPriceOrder(List<WebElement> cardPrices) {
        float previousPrice = 0, currentPrice = 0;
        for (WebElement cardPriceElement : cardPrices) {
            if (!cardPriceElement.isDisplayed()) {
                continue;
            }
            currentPrice = PriceFilterHelper.parsePriceFromResultCard(cardPriceElement.getText());
            if (currentPrice < previousPrice) {
                Assert.fail("Current price could be more or equal Previous price: " +
                        "Previous Prise - " + previousPrice + " | " +
                        "Current Price - " + currentPrice);
            }
            previousPrice = currentPrice;
        }
    }

    public void compareSelectedPriceWithResults(List<WebElement> cardPrices) {
        for (WebElement cardPriceElement : cardPrices) {
            if (!cardPriceElement.isDisplayed()) {
                continue;
            }
            float cardPrice = parsePriceFromResultCard(cardPriceElement.getText());
            if (!compareWithPriceDiapason(cardPrice)) {
                Assert.fail("Current price: " + cardPrice + " not in diapason "
                        + getDiapasonString());
                break;
            }
        }
    }


}
