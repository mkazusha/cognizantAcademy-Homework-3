package webPages;

import baseFunc.BaseFunc;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private BaseFunc baseFunc;
    private final By LOGO = By.xpath(".//img[@class = 'logo img-responsive']");
    private final By SEARCH = By.xpath(".//form[@id='searchbox']");
    private final By HEADER = By.xpath(".//div[@class='header-container']");
    private final By FOOTER = By.xpath(".//footer[@id='footer']");
    private final By TABS = By.xpath(".//div[@id='block_top_menu']/ul/li/a");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
        Assertions.assertFalse(baseFunc.isElementPresent(LOGO), "Logo is not present.");
        Assertions.assertFalse(baseFunc.isElementPresent(SEARCH), "Search is missing.");
        Assertions.assertFalse(baseFunc.isElementPresent(HEADER), "Header is not present.");
        Assertions.assertFalse(baseFunc.isElementPresent(FOOTER), "Footer is not present.");
    }

    public void pushTab(String tab) {
        List<WebElement> tabs = baseFunc.getAllElements(TABS);
        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i).getText().equals(tab)) {
                tabs.get(i).click();
                break;
            }
        }
    }
}
