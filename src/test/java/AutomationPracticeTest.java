import baseFunc.BaseFunc;
import webPages.CartPage;
import webPages.DressesPage;
import webPages.HomePage;
import org.junit.jupiter.api.Test;

public class AutomationPracticeTest {
    private BaseFunc baseFunc = new BaseFunc();
    private HomePage homePage;
    private DressesPage dressesPage;
    private CartPage cartPage;
    private String URL = "http://automationpractice.com/index.php";
    private String orangeColor;
    private double price = 0;

    @Test
    public void test() {
        baseFunc.openPage(URL);
        homePage = new HomePage(baseFunc);
        homePage.pushTab("Dresses");
        dressesPage = new DressesPage(baseFunc);
        dressesPage.selectColor("Orange");
        orangeColor = dressesPage.getOrangeColor();
        dressesPage.checkFilteredItemsColor(dressesPage.getFilteredDresses(), dressesPage.getColorsOfFilteredDresses(), orangeColor);
        dressesPage.pushRandomDress(dressesPage.getFilteredDresses());
        dressesPage.checkSelectedColorOnQuickView(orangeColor);
        baseFunc.switchToMainFrame();
        dressesPage.closeItemQuickView();
        price = dressesPage.addRandomDressToCart(2);
        cartPage = dressesPage.pushCart();
        cartPage.checkCartPriceCalculation(price, cartPage.getTotalProductPrice());
        baseFunc.closeBrowser();
    }
}
