package fr.sewatech.arquillian.ajax.pages;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URL;
import java.util.List;

import static org.jboss.arquillian.graphene.Graphene.waitAjax;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class TalkArqPage {

    @Drone WebDriver browser;
    @ArquillianResource URL baseUrl;

    @FindBy(id = "talks")
    WebElement talks;

    @FindBy(xpath = "//table[@id='talks']/tbody/tr")
    List<WebElement> talkRows;

    @FindBy(id = "speakerSearch")
    WebElement speakerSearchField;

    public TalkArqPage open() {
        browser.get(baseUrl.toString() + "/devoxx.html");
        waitAjax().until(visibilityOf(talks));
        return this;
    }

    public List<WebElement> initialList() {
        return talkRows;
    }

    public List<WebElement> searchBySpeaker(String criteria) {
        speakerSearchField.sendKeys(criteria);
        waitAjax().until(presenceOfElementLocated(By.id("data-loaded")));
        return talkRows;
    }
}
