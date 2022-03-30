package suites;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.homePage.AirWaySelector;
import pages.homePage.Header;
import pages.homePage.HomePage;
import utils.ProjectLogger;

public class TestBase extends TestSuiteBase {

    protected HomePage homePage;

    @BeforeMethod
    public void prepareTest(ITestResult result) {
        homePage = open("https://skyup.aero/uk/", HomePage.class);
        homePage.header = page(Header.class);
        homePage.airWaySelector = page(AirWaySelector.class);

        ProjectLogger.getLogger(this.getClass()).info("Start test: " + result.getMethod().getMethodName());
    }

    @AfterMethod
    public void finishTest(ITestResult result) {
        String testStatus = result.isSuccess() ? "PASSED" : "FAILED";
        ProjectLogger.getLogger(this.getClass()).info("Finish test: " + result.getMethod().getMethodName() + ". Result is: " + testStatus);
        ProjectLogger.getLogger(this.getClass()).info("---------------------------------------------------------------------------------");
    }

    @BeforeClass
    public void addSelenideAllureListener(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }
}
