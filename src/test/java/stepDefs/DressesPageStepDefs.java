package stepDefs;

import baseFunc.BaseFunc;
import baseFunc.CartPage;
import baseFunc.DressesPage;
import baseFunc.HomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class DressesPageStepDefs {
    private BaseFunc baseFunc = new BaseFunc();
    private HomePage homePage;
    private DressesPage dressesPage;
    private CartPage cartPage;
    private String URL = "http://automationpractice.com/index.php";
    private String orangeColor;
    private double price;

    @Given("\"automationpractice.com\" home page")
    public void automationpractice_com_home_page() {
        baseFunc.openPage(URL);
        homePage = new HomePage(baseFunc);
    }

    @When("user selected {string} section")
    public void user_selected_Dress_section() {
        dressesPage = homePage.pushDresses();
    }

    @And("selected {string} color in the filter")
    public void selected_orange_color_in_the_filter() {
        dressesPage.pushOrange();
        orangeColor = dressesPage.getOrangeColor();
    }

    @Then("on the page appeared dresses with {string} color")
    public void on_the_page_appeared_dresses_with_orange_color() {
        orangeColor = dressesPage.getOrangeColor();
        dressesPage.checkFilteredItemsColor(dressesPage.getFilteredDresses(), dressesPage.getColorsOfFilteredDresses(), orangeColor);
    }

    @When("user opens any dress")
    public void user_opens_any_dress() {
        dressesPage.pushRandomDress(dressesPage.getFilteredDresses());
    }

    @Then("{string} color is already selected")
    public void color_is_already_selected() {
        dressesPage.checkSelectedColorOnQuickView(orangeColor);
        baseFunc.switchToMainFrame();
        dressesPage.closeItemQuickView();
    }

    @When("user adds {int} different dresses in the cart")
    public void user_adds_two_different_dresses_in_the_cart() {
        for (int i = 0; i < 2; i++) {
            dressesPage.pushRandomDress(dressesPage.getFilteredDresses());
            price += dressesPage.getDressPrice();
            dressesPage.pushAddToCart();
            dressesPage.pushContinueShopping();
            baseFunc.switchToMainFrame();
        }
    }

    @And("opens cart")
    public void opens_cart() {
        cartPage = dressesPage.pushCart();
    }

    @Then("{string} is calculated correctly")
    public void amount_is_calculated_correctly() {
        cartPage.checkCartPriceCalculation(price, cartPage.getTotalProductPrice());
        baseFunc.closeBrowser();
    }

}
