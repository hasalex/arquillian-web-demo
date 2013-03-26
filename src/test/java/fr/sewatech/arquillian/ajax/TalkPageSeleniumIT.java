package fr.sewatech.arquillian.ajax;

import fr.sewatech.arquillian.ajax.pages.TalkPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class TalkPageSeleniumIT {

    static final String MOI = "Alexis Hassler";

    static WebDriver browser;
    static String baseUrl;
    static TalkPage talkPage;

    @BeforeClass
    public static void initialize() {
        browser = new FirefoxDriver();
        baseUrl = "http://localhost:8180/demo";
        talkPage = new TalkPage(browser, baseUrl);
    }

    @AfterClass
    public static void thisIsTheEnd() {
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
