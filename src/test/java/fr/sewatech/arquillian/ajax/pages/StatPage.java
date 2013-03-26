package fr.sewatech.arquillian.ajax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.util.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class StatPage {

    WebDriver browser;
    WebDriverWait wait;
    String baseUrl;

    @FindBy(id = "clear")
    WebElement clearButton;

    @FindBy(tagName = "p")
    List<WebElement> stats;

    public StatPage(WebDriver browser, String baseUrl) {
        this.browser = browser;
        this.baseUrl = baseUrl;
        this.wait = new WebDriverWait(browser, 1);
        PageFactory.initElements(browser, this);
    }

    public StatPage open() {
        browser.get(baseUrl.toString() + "stats.html");
        wait.until(visibilityOf(clearButton));
        return this;
    }

    public List<WebElement> getStats() {
        return stats;
    }

    public List<WebElement> clear() {
        clearButton.click();
        wait.until(textToBePresentInElement(By.tagName("p"), ": 0"));
        return stats;
    }
}
