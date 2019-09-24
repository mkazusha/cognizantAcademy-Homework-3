package stepDefs;

import baseFunc.BaseFunc;
import webPages.CartPage;
import webPages.DressesPage;
import webPages.HomePage;
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

    @Given("\"automationpractice.com\" homepage")
    public void open_page() {
        baseFunc.openPage(URL);
        homePage = new HomePage(baseFunc);
    }

    @Given("we select {string} section")
    public void push_dress_section(String tab) {
        homePage.pushTab(tab);
        dressesPage = new DressesPage(baseFunc);
    }

    @When("we select {string} color in the filter")
    public void select_filter_color(String color) {
        dressesPage.selectColor(color);
        orangeColor = dressesPage.getOrangeColor();
    }

    @Then("on the page appeared dresses with orange color")
    public void check_items_color() {
        dressesPage.checkFilteredItemsColor(dressesPage.getFilteredDresses(), dressesPage.getColorsOfFilteredDresses(), orangeColor);
    }

    @When("we open any dress")
    public void open_random_dress() {
        dressesPage.pushRandomDress(dressesPage.getFilteredDresses());
    }

    @Then("{string} color is already selected")
    public void check_selected_color(String orangeColor) {
        dressesPage.checkSelectedColorOnQuickView(orangeColor);
    }

    @When("we add {int} different dresses in the cart")
    public void add_dress_to_cart(int numberOfDresses) {
        price = dressesPage.addRandomDressToCart(numberOfDresses);
    }

    @When("we open cart")
    public void open_cart() {
        cartPage = dressesPage.pushCart();
    }

    @Then("products are calculated correctly")
    public void check_cart_calculation() {
        cartPage.checkCartPriceCalculation(price, cartPage.getTotalProductPrice());
    }

    @Then("we close browser")
    public void close_browser() {
        baseFunc.closeBrowser();
    }

    @When("we add one dress {int} times")
    public void add_dress(int times) {
        price = dressesPage.addDressToCart(times);
    }

    @Then("in cart added {int} same dresses")
    public void check_cart_dress(int productNumber) {
        cartPage.checkQty(productNumber);
    }

    @When("we delete {int} dress")
    public void decrese_qty(int itemNumber) {
        cartPage.decreaseQty(itemNumber);
    }

    @When("we put price range between {int} and {int}")
    public void move_range_slider(int minPrice, int maxPrice) {
        baseFunc.moveRangeSlider(minPrice, maxPrice);
    }

    @Then("on page appeared dresses with price in selected range")
    public void check_price_filter() {
        dressesPage.checkPriceRange();
    }


}
