package suites;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ProjectLogger;


public class TestSuiteBase {

    @BeforeSuite
    public void prepareSuite(ITestContext ctx) {
        Configuration.browserSize = "1920x1080";
        //Configuration.holdBrowserOpen = true;
        Configuration.webdriverLogsEnabled = false;
        ScreenShooter.captureSuccessfulTests = true;

        ProjectLogger.getLogger(this.getClass()).info("Start run suite: " + ctx.getSuite().getName());
    }

    @AfterSuite
    public void finishSuite(ITestContext ctx) {
        ProjectLogger.getLogger(this.getClass()).info("Finish run suite: " + ctx.getSuite().getName());
        ProjectLogger.getLogger(this.getClass()).info("---------------------------------------------------------------------------------");
    }
}
