package fr.sewatech.arquillian.ajax.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PagesUtils {
    public static JavascriptUtil js(WebDriver browser) {
        return new JavascriptUtil((JavascriptExecutor) browser);
    }

    public static void waitForAjax(JavascriptExecutor executor) {
        while (true) {
            Boolean ajaxIsComplete = (Boolean) executor.executeScript("return jQuery.active == 0");
            if (ajaxIsComplete) {
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    static class JavascriptUtil {
        private final JavascriptExecutor executor;

        public JavascriptUtil(JavascriptExecutor executor) {
            this.executor = executor;
        }

        public void focus(WebElement element) {
            executor.executeScript("document.getElementById('" + element.getAttribute("id") + "').focus();");
        }
    }
}
