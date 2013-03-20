package fr.sewatech.arquillian.ajax;

import fr.sewatech.arquillian.ajax.pages.TalkPage;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class TalkPageArqTest {

    static final String MOI = "Alexis Hassler";

    @Deployment(testable = false)
    public static Archive<?> deploy() {
        return Deployments.deploy();
    }

    WebDriver browser;
    @ArquillianResource URL baseUrl;
    TalkPage talkPage;

    @Before
    public void initialize() {
        browser = new FirefoxDriver();
        talkPage = new TalkPage(browser, baseUrl.toString());
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
