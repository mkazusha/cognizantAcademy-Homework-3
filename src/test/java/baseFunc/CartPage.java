package baseFunc;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class CartPage {
    private final By TOTAL_PRICE = By.xpath(".//td[@id = 'total_product']");
    private final By ORDER_STEPS = By.xpath(".//ul[@id='order_step']");
    private final By CART_NAVIGATION = By.xpath(".//p[@class='cart_navigation clearfix']");
    private final By CART_PAGE_TITLE = By.xpath(".//h1[@id='cart_title']");
    private final By LOGO = By.xpath(".//img[@class = 'logo img-responsive']");
    private final By SEARCH = By.xpath(".//form[@id='searchbox']");
    private final By CART = By.xpath(".//div[@class='shopping_cart']");
    private final By HEADER = By.xpath(".//div[@class='header-container']");
    private final By FOOTER = By.xpath(".//footer[@id='footer']");
    private BaseFunc baseFunc;

    public CartPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
        Assertions.assertFalse(baseFunc.isElementPresent(CART_PAGE_TITLE), "Title is missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(ORDER_STEPS), "Order steps are missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(CART_NAVIGATION), "Cart navigation buttons are missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(LOGO), "Logo is not present.");
        Assertions.assertFalse(baseFunc.isElementPresent(SEARCH), "Search is missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(CART), "Cart is missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(HEADER), "Header is not present.");
        Assertions.assertFalse(baseFunc.isElementPresent(FOOTER), "Footer is not present.");
    }

    public double getTotalProductPrice() {
        return baseFunc.getPrice(TOTAL_PRICE);
    }

    public void checkCartPriceCalculation(double expectedPrice, double actualPrice){
        Assertions.assertEquals(expectedPrice, actualPrice, "Price is calculated wrong!");
    }
}
