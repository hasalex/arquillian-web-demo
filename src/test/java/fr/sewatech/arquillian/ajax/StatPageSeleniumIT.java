package fr.sewatech.arquillian.ajax;

import fr.sewatech.arquillian.ajax.pages.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.*;

public class StatPageSeleniumIT {

    WebDriver browser;
    String baseUrl;
    TalkPage talkPage;
    WebDriverWait wait;

    @Before
    public void initialize() {
        browser = new FirefoxDriver();
        baseUrl = "http://localhost:8180/demo/";
        talkPage = new TalkPage(browser, baseUrl);
        wait = new WebDriverWait(browser, 1);
    }

    @After
    public void thisIsTheEnd() {
        browser.quit();
    }

    @Test
    public void should_clear_retrieve_0() throws InterruptedException {
        talkPage.open();
        talkPage.searchBySpeaker("azerty");

        browser.get(baseUrl.toString() + "stats.html");
        browser.findElement(By.id("clear")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(By.tagName("p"), ": 0"));
//        List<WebElement> statLines = browser.findElements(By.tagName("p"));
//        assertEquals("total", "Nombre total de recherche : 0", statLines.get(0).getText());
    }
}
