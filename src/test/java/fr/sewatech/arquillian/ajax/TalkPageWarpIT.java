package fr.sewatech.arquillian.ajax;

import fr.sewatech.arquillian.ajax.backend.*;
import fr.sewatech.arquillian.ajax.pages.*;
import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.drone.api.annotation.*;
import org.jboss.arquillian.graphene.spi.annotations.*;
import org.jboss.arquillian.junit.*;
import org.jboss.arquillian.test.api.*;
import org.jboss.arquillian.warp.*;
import org.jboss.arquillian.warp.client.filter.http.*;
import org.jboss.arquillian.warp.servlet.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;
import org.openqa.selenium.*;

import javax.servlet.http.*;
import java.net.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@WarpTest
public class TalkPageWarpIT {

    static final String MOI = "Alexis Hassler";

    @Deployment(testable = true)
    public static WebArchive deploy() {
        return Deployments.deploy();
    }

    @ArquillianResource URL baseUrl;
    @Drone WebDriver browser;
    @Page TalkPage talkPage;

    @Test @RunAsClient
    public void should_my_own_talks_list_have_2_lines() {

        talkPage.open();
        Warp.initiate(new Activity() {
                @Override
                public void perform() {
                    assertEquals("Liste des talks", 2, talkPage.searchBySpeaker(MOI).size());
                }
            })
            .observe(HttpFilters.request().uri().contains("Hassler"))
            .inspect(new Inspection() {
                private static final long serialVersionUID = 1L;

                @ArquillianResource
                HttpServletRequest request;

                @AfterServlet
                public void after() {
                    Statistics statistics = (Statistics) request.getSession().getAttribute(Statistics.NAME);
                    assertEquals("Statistiques", 1, statistics.getDistinctSearchCount());
                }
            });

    }
}
