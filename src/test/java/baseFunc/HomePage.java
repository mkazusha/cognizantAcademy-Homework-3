package baseFunc;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class HomePage {
    private BaseFunc baseFunc;
    private final By DRESSES_BTN = By.xpath("(.//a[@title = 'Dresses'])[2]");
    private final By LOGO = By.xpath(".//img[@class = 'logo img-responsive']");
    private final By SEARCH = By.xpath(".//form[@id='searchbox']");
    private final By CART = By.xpath(".//div[@class='shopping_cart']");
    private final By HEADER = By.xpath(".//div[@class='header-container']");
    private final By FOOTER = By.xpath(".//footer[@id='footer']");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
        Assertions.assertFalse(baseFunc.isElementPresent(LOGO), "Logo is not present.");
        Assertions.assertFalse(baseFunc.isElementPresent(SEARCH), "Search is missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(CART), "Cart is missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(HEADER), "Header is not present.");
        Assertions.assertFalse(baseFunc.isElementPresent(FOOTER), "Footer is not present.");
    }

    public DressesPage pushDresses() {
        baseFunc.getElement(DRESSES_BTN).click();
        return new DressesPage(baseFunc);
    }
}
