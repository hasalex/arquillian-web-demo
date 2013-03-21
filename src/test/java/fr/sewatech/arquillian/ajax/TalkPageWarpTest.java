package fr.sewatech.arquillian.ajax;

import fr.sewatech.arquillian.ajax.backend.Statistics;
import fr.sewatech.arquillian.ajax.pages.TalkArqPage;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.spi.annotations.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.Activity;
import org.jboss.arquillian.warp.Inspection;
import org.jboss.arquillian.warp.Warp;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.arquillian.warp.servlet.AfterServlet;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@WarpTest
public class TalkPageWarpTest {

    static final String MOI = "Alexis Hassler";

    @Deployment(testable = true)
    public static Archive<?> deploy() {
        return Deployments.deploy();
    }

    @Drone WebDriver browser;
    @ArquillianResource URL baseUrl;
    @Page TalkArqPage talkPage;

    @Test @Ignore
    public void should_full_list_have_some_lines() {
        talkPage.open();
        assertEquals("Liste des talks", 10, talkPage.initialList().size());
    }

    @Test @RunAsClient
    public void should_filtered_list_have_less_lines() {
        talkPage.open();

        Warp.initiate(new Activity() {
            @Override
            public void perform() {
                assertEquals("Liste des talks", 2, talkPage.searchBySpeaker(MOI).size());
            }
        }).inspect(new Inspection() {
            private static final long serialVersionUID = 1L;

            @ArquillianResource
            HttpServletRequest request;

            @AfterServlet
            public void after(){
                Statistics statistics = (Statistics) request.getSession().getAttribute("statistics");
                assertEquals("Statistiques", 1, statistics.getDistinctSearchCount());
            }
        });
    }
}
