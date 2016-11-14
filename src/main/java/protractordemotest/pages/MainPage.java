package protractordemotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import protractordemotest.elements.Table;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class MainPage extends BasePage {

    private static final String MAIN_PAGE_URL = "http://juliemr.github.io/protractor-demo/";

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getFirstNumberInput() {
        return driver.findElement(By.xpath("//input[@ng-model='first']"));
    }

    private WebElement getSecondNumberInput() {
        return driver.findElement(By.xpath("//input[@ng-model='second']"));
    }

    private WebElement getCalculatorOperator() {
        return driver.findElement(By.xpath("//select[@ng-model='operator']"));
    }

    private WebElement getButtonGo() {
        return driver.findElement(By.id("gobutton"));
    }

    private WebElement getResult() {
        return driver.findElement(By.tagName("h2"));
    }

    private List<WebElement> getTableRow(final int index) {
        return new Table(driver.findElement(By.tagName("table"))).getRow(index);
    }

    @Override
    protected void load() {
        this.driver.get(MAIN_PAGE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        String currentUrl = this.driver.getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }

    public MainPage setFirstNumber(final String firstNumber) {
        this.getFirstNumberInput().sendKeys(firstNumber);
        return this;
    }

    public MainPage setSecondNumber(final String secondNumber) {
        this.getSecondNumberInput().sendKeys(secondNumber);
        return this;
    }

    public MainPage setOperator(final String operator) {
        this.getCalculatorOperator().sendKeys(operator);
        return this;
    }

    public MainPage clickGo() {
        this.getButtonGo().click();
        this.waitForAngularJS();
        return this;
    }

    public MainPage checkResult(final String expected) {
        Assert.assertEquals(this.getResult().getText(), expected);
        return this;
    }

    public MainPage checkLastResultInHistory(final String expectedExpression, final String expectedResult) {
        Assert.assertEquals(this.getTableRow(1).get(1).getText(), expectedExpression);
        Assert.assertEquals(this.getTableRow(1).get(2).getText(), expectedResult);
        return this;
    }
}