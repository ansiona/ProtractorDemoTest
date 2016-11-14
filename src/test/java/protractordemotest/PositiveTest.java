package protractordemotest;

import org.testng.annotations.Test;
import protractordemotest.pages.MainPage;
import protractordemotest.util.FileParser;

public class PositiveTest extends BaseTest {

    @Test
    public void testPositiveScenarios() {
        MainPage mainPage = new MainPage(driver);
        mainPage.get();
        new FileParser().getListOfTestData("PositiveTest.txt").forEach(testData -> {
                    String firstN = testData[0];
                    String operator = testData[1];
                    String secondN = testData[2];
                    String result = testData[3];
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
}