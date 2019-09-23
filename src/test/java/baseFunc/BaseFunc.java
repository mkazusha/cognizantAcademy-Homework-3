package baseFunc;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseFunc {
    private final By SLIDER_HANDLER = By.xpath(".//a[@class='ui-slider-handle ui-state-default ui-corner-all']");
    private final By PRICE_RANGE = By.xpath(".//span[@id = 'layered_price_range']");
    private WebDriver driver;
    private WebDriverWait wait;
    private String price;
    private float minRangePrice;
    private float maxRangePrice;


    public BaseFunc() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Maria/Projects/CognizantAcademy/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    public void openPage(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        driver.get(url);
    }

    public WebElement getElement(By locator) {
        Assertions.assertFalse(driver.findElements(locator).isEmpty(), "Element is not found!");
        return driver.findElement(locator);
    }


    public List<WebElement> getAllElements(By locator) {
        Assertions.assertFalse(driver.findElements(locator).isEmpty());
        return driver.findElements(locator);
    }

    public boolean isElementPresent(By locator) {
        waitForElement(locator);
        return getAllElements(locator).isEmpty();
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void switchToFrame(By locator) {
        driver.switchTo().frame(getElement(locator));
    }

    public void switchToMainFrame() {
        driver.switchTo().parentFrame();
    }

    public double getPrice(By locator) {
        price = getElement(locator).getText();
        price = price.substring(1);
        double convertedPrice = Double.parseDouble(price);
        return convertedPrice;
    }

    public void moveRangeSlider(float min, float max) {
        List<WebElement> sliderHandlers = getAllElements(SLIDER_HANDLER);
        Actions action = new Actions(driver);
        minRangePrice = convertRangePrice(getElement(PRICE_RANGE).getText().substring(1, 6));
        maxRangePrice = convertRangePrice(getElement(PRICE_RANGE).getText().substring(10));

        if (min > minRangePrice) {
            for (int i = 0; i < min; i++) {
                action.click(sliderHandlers.get(0)).build().perform();
                action.sendKeys(Keys.ARROW_RIGHT).build().perform();
                minRangePrice = convertRangePrice(getElement(PRICE_RANGE).getText().substring(1, 6));
                if (minRangePrice > min) {
                    action.sendKeys(Keys.ARROW_LEFT).build().perform();
                    break;
                }
            }
        }
        if (max < maxRangePrice) {
            for (int i = 0; i < max; i++) {
                action.click(sliderHandlers.get(1)).build().perform();
                action.sendKeys(Keys.ARROW_LEFT).build().perform();
                maxRangePrice = convertRangePrice(getElement(PRICE_RANGE).getText().substring(10));
                if (maxRangePrice < max) {
                    action.sendKeys(Keys.ARROW_RIGHT).build().perform();
                    break;
                }
            }
        }
    }

    public float convertRangePrice(String price) {
        return Float.parseFloat(price);

    }

    public void closeBrowser() {
        driver.quit();
    }
}
