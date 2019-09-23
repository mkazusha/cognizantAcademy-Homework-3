package webPages;

import baseFunc.BaseFunc;
import net.bytebuddy.dynamic.loading.ByteArrayClassLoader;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage {
    private final By TOTAL_PRICE = By.xpath(".//td[@class='cart_total']/span");
    private final By ORDER_STEPS = By.xpath(".//ul[@id='order_step']");
    private final By CART_NAVIGATION = By.xpath(".//p[@class='cart_navigation clearfix']");
    private final By CART_PAGE_TITLE = By.xpath(".//h1[@id='cart_title']");
    private final By FOOTER = By.xpath(".//footer[@id='footer']");
    private final By PRODUCT = By.xpath(".//input[@class='cart_quantity_input form-control grey']");
    private final By DECREASE_BTN = By.xpath(".//a[@class='cart_quantity_down btn btn-default button-minus']");

    private BaseFunc baseFunc;

    public CartPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
        Assertions.assertFalse(baseFunc.isElementPresent(CART_PAGE_TITLE), "Title is missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(ORDER_STEPS), "Order steps are missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(CART_NAVIGATION), "Cart navigation buttons are missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(FOOTER), "Footer is not present.");
    }

    public double getTotalProductPrice() {
        return baseFunc.getPrice(TOTAL_PRICE);
    }

    public void checkCartPriceCalculation(double expectedPrice, double actualPrice){
        Assertions.assertEquals(expectedPrice, actualPrice, "Price is calculated wrong!");
    }

    public void checkQty(int expectedValue) {
        String actualValue = baseFunc.getElement(PRODUCT).getAttribute("value");
        int convertedValue = Integer.parseInt(actualValue);
        Assertions.assertEquals(expectedValue, convertedValue);
    }

    public void decreaseQty(int decreaseValue) {
       baseFunc.getElement(DECREASE_BTN).click();
    }


}
