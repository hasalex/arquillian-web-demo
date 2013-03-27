package fr.sewatech.arquillian.ajax;

import fr.sewatech.arquillian.ajax.pages.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;

import java.util.*;

import static org.junit.Assert.*;

public class StatPageSeleniumIT {

    WebDriver browser;
    String baseUrl;
    TalkPage talkPage;
    StatPage statPage;

    @Before
    public void initialize() {
        browser = new FirefoxDriver();
        baseUrl = "http://localhost:8180/demo/";
        talkPage = new TalkPage();
        statPage = new StatPage(browser, baseUrl);
    }

    @After
    public void thisIsTheEnd() {
        browser.quit();
    }

    @Test
    public void should_clear_retrieve_0() throws InterruptedException {
        talkPage.open().searchBySpeaker("azerty");
        List<WebElement> statElements = statPage.open().clear();
        assertEquals("total", "Nombre total de recherche : 0", statElements.get(0).getText());
    }
}
