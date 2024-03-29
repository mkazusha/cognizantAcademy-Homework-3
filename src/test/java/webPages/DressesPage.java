package webPages;

import baseFunc.BaseFunc;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class DressesPage {
    private final By BANNER_NAME = By.xpath(".//div[@class = 'cat_desc']/span");
    private final By ORANGE_FILTER = By.xpath(".//input[@id = 'layered_id_attribute_group_13']");
    private final By QUICK_VIEW_BTN = By.xpath(".//a[@class='product_img_link']");
    private final By QUICK_VIEW = By.xpath(".//iframe[@class='fancybox-iframe']");
    private final By SELECTED_COLOR = By.xpath(".//li[@class='selected']");
    private final By CLOSE_QUICK_VIEW_BTN = By.xpath(".//a[@class = 'fancybox-item fancybox-close']");
    private final By ADD_TO_CART = By.xpath(".//button[@type='submit']");
    private final By CART = By.xpath(".//b[contains(text(), 'Cart')]");
    private final By CONTINUE_SHOPPING_BTN = By.xpath(".//i[@class='icon-chevron-left left']");
    private final By DRESSES = By.xpath(".//div[@class = 'product-container']");
    private final By COLORS = By.xpath(".//a[@class = 'color_pick']");
    private final By PRICE = By.xpath(".//span[@id='our_price_display']");
    private final By CATALOG_FILTERS = By.xpath(".//div[@id='layered_block_left']");
    private final By HEADER = By.xpath(".//div[@class='header-container']");
    private final By COLOR_FILTERS = By.xpath(".//label[@class='layered_color']/a");
    private final By PRICE_RANGE = By.xpath(".//span[@id = 'layered_price_range']");
    private int match = 0;
    private double totalPrice = 0;
    private float minRangePrice;
    private float maxRangePrice;
    String test_var;
    Random rand = new Random();

    private BaseFunc basefunc;
    private String bannerName = "Dresses";

    public DressesPage(BaseFunc baseFunc) {
        this.basefunc = baseFunc;
        Assertions.assertEquals(baseFunc.getElement(BANNER_NAME).getText(), bannerName, "Wrong Banner name");
        Assertions.assertFalse(baseFunc.isElementPresent(CART), "Cart is missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(CATALOG_FILTERS), "Filters are missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(HEADER), "Header is not present.");
    }

    public void selectColor(String color) {
        List<WebElement> filterColors = basefunc.getAllElements(COLOR_FILTERS);
        for (int i = 0; i < filterColors.size(); i++) {
            if (filterColors.get(i).getText().contains(color)) {
                filterColors.get(i).click();
                break;
            }
        }
    }

    public void checkFilteredItemsColor(List<WebElement> filteredItems, List<WebElement> colors, String colorToCheck) {
        for (WebElement color : colors) {
            if (isColorEqual(colorToCheck, color)) match++;
        }
        Assertions.assertEquals(filteredItems.size(), match, "Filter is not working!!! Matched only " + match + " of " + filteredItems.size());
    }

    public boolean isColorEqual(String colorToCheck, WebElement color) {
        return colorToCheck.equals(color.getAttribute("style"));
    }


    public void pushRandomDress(List<WebElement> dresses) {
        dresses.get(rand.nextInt(dresses.size())).findElement(QUICK_VIEW_BTN).click();
        basefunc.waitForElement(QUICK_VIEW);
        basefunc.switchToFrame(QUICK_VIEW);
    }


    public String getActualColor(By locator) {
        WebElement element = basefunc.getElement(locator);
        return element.findElement(By.tagName("a")).getAttribute("name");
    }

    public void checkSelectedColorOnQuickView(String expectedColor) {
        System.out.println(getActualColor(SELECTED_COLOR));
        Assertions.assertEquals(expectedColor, getActualColor(SELECTED_COLOR), "Orange color is not selected by default!!!");
        basefunc.switchToMainFrame();
        closeItemQuickView();
    }

    public void closeItemQuickView() {
        basefunc.waitForElement(CLOSE_QUICK_VIEW_BTN);
        basefunc.getElement(CLOSE_QUICK_VIEW_BTN).click();
    }

    public void pushAddToCart() {
        basefunc.getElement(ADD_TO_CART).click();
    }

    public CartPage pushCart() {
        basefunc.getElement(CART).click();
        return new CartPage(basefunc);
    }

    public void pushContinueShopping() {
        basefunc.waitForElement(CONTINUE_SHOPPING_BTN);
        basefunc.getElement(CONTINUE_SHOPPING_BTN).click();
    }

    public List<WebElement> getFilteredDresses() {
        return basefunc.getAllElements(DRESSES);
    }

    public List<WebElement> getColorsOfFilteredDresses() {
        return basefunc.getAllElements(COLORS);
    }

    public String getOrangeColor() {
        return basefunc.getElement(ORANGE_FILTER).getAttribute("style");
    }

    public double getDressPrice() {
        return basefunc.getPrice(PRICE);
    }

    public double addRandomDressToCart(int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            pushRandomDress(getFilteredDresses());
            totalPrice += getDressPrice();
            pushAddToCart();
            pushContinueShopping();
            basefunc.switchToMainFrame();
        }
        return totalPrice;
    }

    public double addDressToCart(int dressQty) {
        int random = rand.nextInt(getFilteredDresses().size());
        for (int i = 0; i < dressQty; i++) {
            getFilteredDresses().get(random).findElement(QUICK_VIEW_BTN).click();
            basefunc.switchToFrame(QUICK_VIEW);
            totalPrice += getDressPrice();
            pushAddToCart();
            pushContinueShopping();
            basefunc.switchToMainFrame();
        }
        return totalPrice;
    }

    public void checkPriceRange() {
        minRangePrice = basefunc.convertRangePrice(basefunc.getElement(PRICE_RANGE).getText().substring(1, 6));
        maxRangePrice = basefunc.convertRangePrice(basefunc.getElement(PRICE_RANGE).getText().substring(10));
        for (int i = 0; i < getFilteredDresses().size(); i++) {
            getFilteredDresses().get(i).findElement(QUICK_VIEW_BTN).click();
            basefunc.switchToFrame(QUICK_VIEW);
            double actualPrice;
            actualPrice = getDressPrice();
            System.out.println(i);
            Assertions.assertFalse(actualPrice < minRangePrice, "Price range slider is not working!");
            Assertions.assertFalse(actualPrice > maxRangePrice, "Price range slider is not working!");
            basefunc.switchToMainFrame();
            closeItemQuickView();
        }

    }
}
