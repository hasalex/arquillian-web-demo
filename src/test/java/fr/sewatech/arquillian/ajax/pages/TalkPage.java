package fr.sewatech.arquillian.ajax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class TalkPage {

    WebDriver browser;
    WebDriverWait wait;
    String baseUrl;

    @FindBy(id = "talks")
    WebElement talks;

    @FindBy(xpath = "//table[@id='talks']/tbody/tr")
    List<WebElement> talkRows;

    @FindBy(id = "speakerSearch")
    WebElement speakerSearchField;

    public TalkPage(WebDriver browser, String baseUrl) {
        this.browser = browser;
        this.baseUrl = baseUrl;
        this.wait = new WebDriverWait(browser, 100);
        PageFactory.initElements(browser, this);
    }

    public TalkPage open() {
        browser.get(baseUrl.toString() + "/devoxx.html");
        wait.until(visibilityOf(talks));
        return this;
    }

    public List<WebElement> initialList() {
        return talkRows;
    }

    public List<WebElement> searchBySpeaker(String criteria) {
        speakerSearchField.sendKeys(criteria);
        wait.until(presenceOfElementLocated(By.id("data-loaded")));
        return talkRows;
    }
}
