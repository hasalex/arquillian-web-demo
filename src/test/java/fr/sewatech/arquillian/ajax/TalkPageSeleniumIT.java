package fr.sewatech.arquillian.ajax;

import fr.sewatech.arquillian.ajax.pages.TalkPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class TalkPageSeleniumIT {

    static final String MOI = "Alexis Hassler";

    WebDriver browser;
    String baseUrl;
    TalkPage talkPage;

    @Before
    public void initialize() {
        browser = new FirefoxDriver();
        baseUrl = "http://localhost:8180/demo";
        talkPage = new TalkPage(browser, baseUrl);
    }

    @After
    public void thisIsTheEnd() {
        browser.quit();
    }

    @Test
    public void should_full_list_have_10_lines() {
        talkPage.open();
        assertEquals("Liste des talks", 10, talkPage.initialList().size());
    }

    @Test
    public void should_my_own_talks_list_have_2_lines() {
        talkPage.open();
        assertEquals("Liste des talks", 2, talkPage.searchBySpeaker(MOI).size());
    }
}
