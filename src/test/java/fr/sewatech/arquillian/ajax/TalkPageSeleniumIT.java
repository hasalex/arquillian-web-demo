package fr.sewatech.arquillian.ajax;

import fr.sewatech.arquillian.ajax.pages.TalkPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
    public void should_full_list_have_some_lines() {
        talkPage.open();
        assertEquals("Liste des talks", 10, talkPage.initialList().size());
    }

    @Test
    public void should_filtered_list_have_less_lines() {
        talkPage.open();
        assertEquals("Liste des talks", 2, talkPage.searchBySpeaker(MOI).size());
    }
}
