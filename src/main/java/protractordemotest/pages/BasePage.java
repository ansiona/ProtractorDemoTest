package protractordemotest.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage extends LoadableComponent<BasePage> {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForAngularJS() {

        final String query =
                "window.angularFinished;waitForAngular = function() {  window.angularFinished = false;"
                        + "  var el = document.querySelector('body');"
                        + "  var callback = (function(){window.angularFinished=1});"
                        + "  angular.element(el).injector().get('$browser')."
                        + "      notifyWhenNoOutstandingRequests(callback);};";

        ((JavascriptExecutor) driver).executeScript(query);

        try {
            ((JavascriptExecutor) driver).executeScript("waitForAngular();");
            new WebDriverWait(driver, 30) {
            }.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    Object noAjaxRequests = ((JavascriptExecutor) driver).executeScript("return window.angularFinished;");
                    return "1".equals(noAjaxRequests.toString());
                }
            });
        } catch (TimeoutException e) {
            String ErrorMsg =
                    "Failed wait for no angular JS request within timeout of : " + 30 + " seconds  at url:"
                            + driver.getCurrentUrl();
            System.out.println(ErrorMsg);
        }
    }
}