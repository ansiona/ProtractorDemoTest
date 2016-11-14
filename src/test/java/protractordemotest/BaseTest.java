package protractordemotest;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import protractordemotest.util.PropertyLoader;
import ru.stqa.selenium.factory.WebDriverPool;

import java.io.IOException;

public class BaseTest {

    protected static Capabilities capabilities;
    protected WebDriver driver;

    @BeforeSuite
    public void initTestSuite() throws IOException {
        capabilities = PropertyLoader.loadCapabilities();
    }

    @BeforeMethod
    public void initWebDriver() {
        driver = WebDriverPool.DEFAULT.getDriver(null, capabilities);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        WebDriverPool.DEFAULT.dismissAll();
    }
}