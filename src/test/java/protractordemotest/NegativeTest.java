package protractordemotest;

import org.testng.annotations.Test;
import protractordemotest.pages.MainPage;
import protractordemotest.util.FileParser;

import java.util.Arrays;

public class NegativeTest extends BaseTest {

    @Test
    public void testNegativeScenarios() {
        MainPage mainPage = new MainPage(driver);
        mainPage.get();
        new FileParser().getListOfTestData("NegativeTest.txt").forEach(testData -> {
                    String firstN = testData[0];
                    String operator = testData[1];
                    String secondN = testData[2];
                    String result = "NaN";
                    mainPage
                            .setFirstNumber(firstN)
                            .setOperator(operator)
                            .setSecondNumber(secondN)
                            .clickGo()
                            .checkResult(result)
                            .checkLastResultInHistory(String.format("%s %s %s", firstN, operator, secondN), result);
                }
        );
    }

    @Test
    public void testEmptyValues() {
        MainPage mainPage = new MainPage(driver);
        mainPage.get();
        Arrays.asList("+", "-", "*", "/").forEach(operator -> {
                    String result = "NaN";
                    mainPage
                            .setFirstNumber("")
                            .setOperator(operator)
                            .setSecondNumber("")
                            .clickGo()
                            .checkResult(result)
                            .checkLastResultInHistory(operator, result);
                }
        );
    }
}