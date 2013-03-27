package fr.sewatech.arquillian.ajax.pages;

import org.jboss.arquillian.drone.api.annotation.*;
import org.jboss.arquillian.test.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.net.*;
import java.util.*;

import static org.jboss.arquillian.graphene.Graphene.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class TalkPage {

    @Drone WebDriver browser;
    @ArquillianResource URL baseUrl;

    @FindBy(id = "talks")
    WebElement talks;

    @FindBy(xpath = "//table[@id='talks']/tbody/tr")
    List<WebElement> talkRows;

    @FindBy(id = "speakerSearch")
    WebElement speakerSearchField;

    public TalkPage open() {
        browser.get(baseUrl.toString() + "devoxx.html");
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
