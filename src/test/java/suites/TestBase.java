package suites;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.homePage.AirWaySelector;
import pages.homePage.Header;
import pages.homePage.HomePage;
import projectConfig.ProjectConfiguration;
import utils.ProjectLogger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;


public class TestBase extends TestSuiteBase {

    protected HomePage homePage;
    private RemoteWebDriver driver;
    private String host;


    @BeforeClass
    public void addSelenideAllureListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        host = projectProperties.getProperty("host");
    }


    @BeforeMethod
    public void prepareTest(ITestResult result) {
        if (host.equals("saucelabs")) {
            driver = setDriverCapabilities(ProjectConfiguration.getProjectProperties(), result);
            WebDriverRunner.setWebDriver(driver);
        }

        homePage = open("https://skyup.aero/uk/", HomePage.class);
        homePage.header = page(Header.class);
        homePage.airWaySelector = page(AirWaySelector.class);

        ProjectLogger.getLogger(this.getClass()).info("Start test: " + result.getMethod().getMethodName());
    }


    @AfterMethod
    public void finishTest(ITestResult result) {
        String testStatus = result.isSuccess() ? "passed" : "failed";
        ProjectLogger.getLogger(this.getClass()).info("Finish test: " + result.getMethod().getMethodName() + ". Result is: " + testStatus);
        ProjectLogger.getLogger(this.getClass()).info("---------------------------------------------------------------------------------");

        if (host.equals("saucelabs")) {
            driver.executeScript("sauce:job-result=" + testStatus);
            driver.quit();
        }
    }


    private RemoteWebDriver setDriverCapabilities(Properties projectProperties, ITestResult result) {
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", projectProperties.getProperty("userName"));
        sauceOptions.setCapability("accessKey", projectProperties.getProperty("accessKey"));
        sauceOptions.setCapability("name", result.getMethod().getMethodName());
        sauceOptions.setCapability("screenResolution", projectProperties.getProperty("screenResolution"));

        MutableCapabilities capabilities = new MutableCapabilities();

        String browserName = projectProperties.getProperty("browserName");
        switch (browserName) {
            case "chrome" -> capabilities = new ChromeOptions();
            case "firefox" -> capabilities = new FirefoxOptions();
            case "edge" -> capabilities = new EdgeOptions();
            case "ie" -> capabilities = new InternetExplorerOptions();
        }

        capabilities.setCapability("browserName", projectProperties.getProperty("browserName"));
        capabilities.setCapability("browserVersion", projectProperties.getProperty("browserVersion"));
        capabilities.setCapability("platformName", projectProperties.getProperty("platformName"));
        capabilities.setCapability("name", result.getMethod().getMethodName());
        capabilities.setCapability("sauce:options", sauceOptions);

        URL url = null;
        try {
            url = new URL(projectProperties.getProperty("driverCreationUrl"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(Objects.requireNonNull(url), capabilities);
    }
}
