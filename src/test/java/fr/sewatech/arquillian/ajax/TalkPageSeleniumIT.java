package fr.sewatech.arquillian.ajax;

import fr.sewatech.arquillian.ajax.pages.*;
import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.drone.api.annotation.*;
import org.jboss.arquillian.graphene.spi.annotations.*;
import org.jboss.arquillian.junit.*;
import org.jboss.arquillian.test.api.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;
import org.openqa.selenium.*;

import java.net.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TalkPageSeleniumIT {

    static final String MOI = "Alexis Hassler";

    @Deployment(testable = false)
    public static WebArchive deploy() {
        return Deployments.deploy();
    }

    @ArquillianResource URL baseUrl;
    @Drone WebDriver browser;
    @Page TalkPage talkPage;

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
