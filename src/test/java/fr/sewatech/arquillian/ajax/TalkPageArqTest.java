package fr.sewatech.arquillian.ajax;

import fr.sewatech.arquillian.ajax.pages.TalkArqPage;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.spi.annotations.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class TalkPageArqTest {

    static final String MOI = "Alexis Hassler";

    @Deployment(testable = false)
    public static Archive<?> deploy() {
        return Deployments.deploy();
    }

    @Drone WebDriver browser;
    @ArquillianResource URL baseUrl;
    @Page TalkArqPage talkPage;

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
